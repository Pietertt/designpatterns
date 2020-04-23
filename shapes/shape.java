package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

public abstract class Shape extends JComponent implements receiver, MouseMotionListener {
      public int id;

      // Moet de rectangle getekend worden
      protected boolean rectDraw = false;

      // Is de rectangle geselecteerd
      protected boolean selected = false;

      // Is dragging toggled on
      public boolean dragging = false;

      protected int x;
      protected int y;
      protected int width;
      protected int height;

      // Save width & height to redraw.
      protected Stack<Integer> redoStack;
      protected Stack<Integer> undoStack;
      protected int savedWidth;
      protected int savedHeight;
      protected int savedX;
      protected int savedY;

      public void setColor(Graphics g) {
            if(this.selected)
                  g.setColor(Color.GRAY);
            else
                  g.setColor(Color.BLUE);
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