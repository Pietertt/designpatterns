package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

public class rectangle extends JComponent implements receiver, MouseMotionListener {
      public int id;

      // Moet de rectangle getekend worden
      private boolean rectDraw = false;

      // Is de rectangle geselecteerd
      private boolean selected = false;

      // Is dragging toggled on
      public boolean dragging = false;

      private int x;
      private int y;
      private int width;
      private int height;

      // Save width & height to redraw.
      private Stack<Integer> redoStack;
      private Stack<Integer> undoStack;
      private int savedWidth;
      private int savedHeight;
      private int savedX;
      private int savedY;

      public rectangle(int x, int y, int width, int height, int id){
            //super(x, y, width, height);
            this.x = x;
            this.savedX = x;
            this.y = y;
            this.savedY = y;
            this.width = width;
            this.savedWidth= width;
            this.height = height;
            this.savedHeight = height;
            this.id = id;
            this.undoStack = new Stack<>();
            this.redoStack = new Stack<>();
      }

      private void setColor(Graphics g) {
            if(selected)
                  g.setColor(Color.GRAY);
            else
                  g.setColor(Color.BLUE);
      }

      @Override
      public void paintComponent(Graphics g) {
            if(rectDraw) {
                  super.paintComponent(g);
                  setColor(g);
                  g.fillRect(x, y, width, height);
                  g.drawRect(x, y, width, height);
            }
      }

      public void setDraggingFalse() {
            this.dragging = false;
      }

      public void drag() {
            if(selected) {
                  this.dragging = true;
                  this.undoStack.push(height);
                  this.undoStack.push(width);
                  this.undoStack.push(y);
                  this.undoStack.push(x);
                  repaint();
            }
      }

      public void redoDrag() {
            //this.dragging = true;

            this.undoStack.push(height);
            this.undoStack.push(width);
            this.undoStack.push(y);
            this.undoStack.push(x);

            this.x = this.redoStack.pop();
            this.y = this.redoStack.pop();
            this.width = this.redoStack.pop();
            this.height = this.redoStack.pop();
            repaint();
      }

      public void undoDrag() {
            //this.dragging = false;

            this.redoStack.push(height);
            this.redoStack.push(width);
            this.redoStack.push(y);
            this.redoStack.push(x);

            this.x = this.undoStack.pop();
            this.y = this.undoStack.pop();
            this.width = this.undoStack.pop();
            this.height = this.undoStack.pop();

            repaint();
      }



      public void setDrawTrue() {
            this.width = savedWidth;
            this.height = savedHeight;
            rectDraw = true;
            repaint();
      }

      public void setDrawFalse() {
            // size has to be set to zero to ensure you don't select invisible rectangles
            this.width = 0;
            this.height = 0;
            rectDraw = false;
            repaint();
      }

      public void setSelectedTrue() {
            // can only select when rectangle is actually drawn
            if(rectDraw)
                  selected = true;
            repaint();
      }

      public void setSelectedFalse() {
            // can only deselect when rectangle is actually drawn
            if(rectDraw)
                  selected = false;
            repaint();
      }

      public boolean getSelected() {
            return selected;
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

      @Override
      public void mouseDragged(MouseEvent e) {
            if(selected && dragging) {
                  this.x = e.getX();
                  this.y = e.getY();
                  repaint();
            }
      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }
}