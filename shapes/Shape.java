package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

import commands.*;
import UI.*;
import visitor.Visitor;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;

public abstract class Shape extends JComponent implements MouseMotionListener, MouseListener, Border {
      public int x;
      public int y;
      public int width;
      public int height;

      private int size = 8;

      int locations[] = { SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST, SwingConstants.EAST,
            SwingConstants.NORTH_WEST, SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
            SwingConstants.SOUTH_EAST };

int cursors[] = { Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR, Cursor.E_RESIZE_CURSOR,
            Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR };


      /*
            A class is used to store the x, y, width and height
      */
      protected Stack<Location> redoStack = new Stack<Location>();
      protected Stack<Location> undoStack = new Stack<Location>();

      public int cursor;
      public Point start = null;
      public Invoker invoker;
      public Board board;

      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      @Override
      public Insets getBorderInsets(Component component) {
            return new Insets(this.size, this.size, this.size, this.size);
      }

      @Override
      public boolean isBorderOpaque() {
            return false;
      }

      @Override
      public void paintBorder(Component component, Graphics g, int x, int y, int width, int height) {
            // if (component.hasFocus()) {
                  for (int i = 0; i < locations.length; i++) {
                        var rect = getRectangle(x, y, width, height, locations[i]);
                        g.setColor(Color.WHITE);
                        g.fillOval(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
                        g.setColor(new Color(80, 155, 229));
                        g.fillOval(rect.x, rect.y, rect.width, rect.height);
                  }
            //}
      }

      private Rectangle getRectangle(int x, int y, int width, int height, int location) {
            switch (location) {
                  case SwingConstants.SOUTH_EAST:
                        return new Rectangle(x + width - 8, y + height - this.size, 8, 8);
                  default:
                        return new Rectangle(0, 0, 0, 0);
            }
      }

      public int getCursor(MouseEvent e) {
            Component component = e.getComponent();
            int width = component.getWidth();
            int height = component.getHeight();

            for (int i = 0; i < locations.length; i++) {
                  var rect = getRectangle(0, 0, width, height, locations[i]);
                  System.out.println(width);
                  if (rect.contains(e.getPoint())) {
                        return cursors[i];
                  }
            }
            return Cursor.MOVE_CURSOR;
      }

      public abstract void accept(Visitor visitor);

      public void place(Invoker invoker, Board board){
            this.invoker = invoker;
            this.board = board;

            JPanel area = new JPanel();
            setOpaque(true);

            setBounds(this.x, this.y, this.width, this.height);
            add(area);
            addMouseListener(this);
            addMouseMotionListener(this);
            this.drawed = true;
            repaint();
      }

      public void remove(){
            /*
                  Sets the drawed flag to false. 
                  The shape is hidden by doing this. Repaint is immediatly called to redraw the shape
            */           
            this.drawed = false;
            repaint();
      }

      public void select(MouseEvent e){
            this.selected = true;

            setBorder(new ResizableBorder());
            repaint();
      }

      public void deselect(){
            setBorder(BorderFactory.createEmptyBorder());
            this.selected = false;
            repaint();
      }

      public void drag(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
      }

      public void undoDrag(){
            Location location = this.undoStack.pop();
            this.redoStack.add(location);

            java.awt.Rectangle bounds = new java.awt.Rectangle();
            bounds.x = location.x;
            bounds.y = location.y;
            bounds.width = location.width;
            bounds.height = location.height;

            setBounds(bounds);
            repaint();
      }

      public void redoDrag(){
            if(this.redoStack.size() > 0){
                  Location location = this.redoStack.pop();
                  this.undoStack.add(location);

                  java.awt.Rectangle bounds = new java.awt.Rectangle();
                  bounds.x = location.x;
                  bounds.y = location.y;
                  bounds.width = location.width;
                  bounds.height = location.height;

                  setBounds(bounds);
                  repaint();
            }
      }

      public void mouseClicked(MouseEvent e){

      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){
            /*
                  Pollution of the undo/redo history is prevented by only adding 
                  DragShapeCommand when the shape has been dragged
            */
            if(this.dragging){
                  Location location = new Location();
                  location.x = getX();
                  location.y = getY();
                  location.width = getWidth();
                  location.height = getHeight();

                  Order drag = new DragShapeCommand(this, location);
                  this.invoker.execute(drag);
                  this.dragging = false;
            }  
            
            if(this.resizing){
                  Location location = new Location();
                  location.x = getX();
                  location.y = getY();
                  location.width = getWidth();
                  location.height = getHeight();

                  // Order drag = new ResizeShapeCommand(this, location);
                  // this.invoker.execute(drag);
                  // this.resizing = false;
            }
      }

      public void mousePressed(MouseEvent e){
            if(this.undoStack.size() == 0){
                  Location location = new Location();
                  location.x = getX();
                  location.y = getY();
                  location.width = getWidth();
                  location.height = getHeight();

                  Order drag = new DragShapeCommand(this, location);
                  this.invoker.execute(drag);
            }

            if(!this.selected){
                  for(Shape shape : board.shapes){
                        if(shape.selected){  
                              Order deselect = new DeselectShapeCommand(shape, e);
                              this.invoker.execute(deselect);
                        }
                  }

                  Order select = new SelectShapeCommand(this, e);
                  this.invoker.execute(select);
                  requestFocus();
            }

            var resizableBorder = (ResizableBorder) getBorder();
            this.cursor = resizableBorder.getCursor(e);
            this.start = e.getPoint();
      }

      @Override
      public void mouseMoved(MouseEvent e) {
            if (this.selected) {
                  var resizableBorder = (ResizableBorder) getBorder();
                  var cursor = resizableBorder.getCursor(e);
                  setCursor(Cursor.getPredefinedCursor(cursor));
            }
      }   


      @Override
      public void mouseDragged(MouseEvent e) {
            if (start != null) {
                  int x = getX();
                  int y = getY();
                  int width = getWidth();
                  int height = getHeight();
                  int dx = e.getX() - this.start.x;
                  int dy = e.getY() - this.start.y;

                  switch (cursor) {
                        case Cursor.SE_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x, y, width + dx, height + dy);
                              start = e.getPoint();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.MOVE_CURSOR:    
                              this.resizing = false;  
                              this.dragging = true;      

                              var bounds = getBounds();
                              bounds.translate(dx, dy);
                              setBounds(bounds);
                              this.width = getWidth();
                              this.height = getHeight();
                              repaint();

                  }
                  setCursor(Cursor.getPredefinedCursor(cursor));
            } 
      }

      public boolean getIfSelected(int x, int y) {
            for(int i = 0; i < this.width; i++){
                  if(x == this.x + i){
                        for(int j = 0; j < this.height; j++){
                              if(y == this.y + j){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }
}