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

      protected Stack<java.awt.Rectangle> redoStack = new Stack<java.awt.Rectangle>();
      protected Stack<java.awt.Rectangle> undoStack = new Stack<java.awt.Rectangle>();

      public int cursor;
      public Point start = null;
      public Invoker invoker;

      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean moving = false;

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

            undoStack.add(getBounds());
      }

      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void select(MouseEvent e){
            this.selected = true;
            setBorder(new ResizableBorder());
            var resizableBorder = (ResizableBorder) getBorder();
            cursor = resizableBorder.getCursor(e);
            start = e.getPoint();
            repaint();
      }

      public void deselect(){
            setBorder(BorderFactory.createEmptyBorder());
            this.selected = false;
            repaint();
      }

      public void drag(java.awt.Rectangle bounds){
            this.undoStack.add(bounds);
      }

      public void undoDrag(){
            java.awt.Rectangle bounds = this.undoStack.pop();
            this.redoStack.add(bounds);
            setBounds(bounds);
            resize();
            repaint();
      }

      public void redoDrag(){
            java.awt.Rectangle bounds = this.redoStack.pop();
            this.undoStack.add(bounds);
            setBounds(bounds);
            resize();
            repaint();
      }

      private void resize() {
            if(getParent() != null){
                  getParent().revalidate();
            }
      }

      public void mouseClicked(MouseEvent e){
            
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){
            Order drag = new DragShapeCommand(this, getBounds());
            this.invoker.execute(drag);            
      }

      public void mousePressed(MouseEvent e){
            Order select = new SelectShapeCommand(this, e);
            this.invoker.execute(select);
            requestFocus();
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
                                    setBounds(x, y + dy, width, height - dy);
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.S_RESIZE_CURSOR:
                                    setBounds(x, y, width, height + dy);
                                    start = e.getPoint();
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.W_RESIZE_CURSOR:
                                    setBounds(x + dx, y, width - dx, height);
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.E_RESIZE_CURSOR:
                                    setBounds(x, y, width + dx, height);
                                    start = e.getPoint();
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.NW_RESIZE_CURSOR:
                                    setBounds(x + dx, y + dy, width - dx, height - dy);
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.NE_RESIZE_CURSOR:
                                    setBounds(x, y + dy, width + dx, height - dy);
                                    start = new Point(e.getX(), start.y);
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.SW_RESIZE_CURSOR:
                                    setBounds(x + dx, y, width - dx, height + dy);
                                    start = new Point(start.x, e.getY());
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.SE_RESIZE_CURSOR:
                                    setBounds(x, y, width + dx, height + dy);
                                    start = e.getPoint();
                                    resize();
                                    this.width = width;
                                    this.height = height;
                                    repaint();
                                    break;
                              case Cursor.MOVE_CURSOR:                                    
                                    var bounds = getBounds();
                                    bounds.translate(dx, dy);
                                    setBounds(bounds);
                                    resize();
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