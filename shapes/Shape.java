package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

import commands.*;
import UI.Invoker;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public abstract class Shape extends JComponent implements MouseMotionListener, MouseListener {
      protected int x;
      protected int y;
      protected int width;
      protected int height;

      /*
            A class is used to store the x, y, width and height
      */
      protected Stack<Location> redoStack = new Stack<Location>();
      protected Stack<Location> undoStack = new Stack<Location>();

      public int cursor;
      public Point start = null;
      public Invoker invoker;

      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public void place(Invoker invoker){
            this.invoker = invoker;
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

                  Order drag = new ResizeShapeCommand(this, location);
                  this.invoker.execute(drag);
                  this.resizing = false;
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
                  setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(e)));
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
                        case Cursor.N_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x, y + dy, width, height - dy);
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.S_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x, y, width, height + dy);
                              start = e.getPoint();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.W_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x + dx, y, width - dx, height);
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.E_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x, y, width + dx, height);
                              start = e.getPoint();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.NW_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x + dx, y + dy, width - dx, height - dy);
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.NE_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x, y + dy, width + dx, height - dy);
                              start = new Point(e.getX(), start.y);
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.SW_RESIZE_CURSOR:
                              this.resizing = true;
                              this.dragging = false;

                              setBounds(x + dx, y, width - dx, height + dy);
                              start = new Point(start.x, e.getY());
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
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

      


      // public void mouseClicked(MouseEvent e){

      // }

      // public void mouseExited(MouseEvent e) {
      //       setCursor(Cursor.getDefaultCursor());
      // }

      // public void mouseReleased(MouseEvent mouseEvent) {
      //       start = null;
      // }

      // public void mousePressed(MouseEvent e) {
      //       var resizableBorder = (ResizableBorder) getBorder();
      //       cursor = resizableBorder.getCursor(e);
      //       start = e.getPoint();

      //       requestFocus();
      //       repaint();
      // }
      
      

      // public void mouseEntered(MouseEvent e){

      // }

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