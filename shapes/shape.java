package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

public abstract class shape extends JComponent implements receiver, MouseMotionListener {
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

      //Is this a root node
      private boolean root = true;

      //saved for dragging groups
      private int savedRootX;
      private int savedRootY;
      private ArrayList<Integer> savedChildX = new ArrayList<>();
      private ArrayList<Integer> savedChildY = new ArrayList<>();


      // Group of shapes
      public ArrayList<shape> shapes;

      public void setColor(Graphics g) {
            if(this.selected)
                  g.setColor(Color.GRAY);
            else
                  g.setColor(Color.BLUE);
      }

      public void setDraggingFalse() {
            for(shape shape : shapes) {
                  shape.setDraggingFalse();
            }

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

                  if(!shapes.isEmpty()) {
                        for (shape shape : shapes) {
                              //shape.setSelectedTrue();
                              shape.drag();
                        }
                  }
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

            if(!shapes.isEmpty()) {
                  for (shape shape : shapes) {
                        shape.redoDrag();
                  }
            }
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

            if(!shapes.isEmpty()) {
                  for (shape shape : shapes) {
                        shape.undoDrag();
                  }
            }
      }



      public void setDrawTrue() {
//            for(shape shape : shapes) {
//                  shape.setDrawTrue();
//            }

            this.width = savedWidth;
            this.height = savedHeight;
            rectDraw = true;
            repaint();
      }

      public void setDrawFalse() {
//            for(shape shape : shapes) {
//                  shape.setDrawFalse();
//            }

            // size has to be set to zero to ensure you don't select invisible rectangles
            this.width = 0;
            this.height = 0;
            rectDraw = false;
            repaint();
      }

      public void setSelectedTrue() {


            // save for dragging
            savedRootX = this.x;
            savedRootY = this.y;
            savedChildX.clear();
            savedChildY.clear();

            if(!shapes.isEmpty()) {
                  for(int i = 0; i < shapes.size(); i++) {
                        savedChildX.add(this.shapes.get(i).x);
                        savedChildY.add(this.shapes.get(i).y);
                  }
            }

            // can only select when rectangle is actually drawn
            if(rectDraw) {
                  selected = true;
                  for (shape shape : shapes) {
                        shape.setSelectedTrue();
                  }
            }
            repaint();
      }

      public void setSelectedFalse() {


            // can only deselect when rectangle is actually drawn
            if(rectDraw) {
                  selected = false;
                  for (shape shape : shapes) {
                        shape.setSelectedFalse();
                  }
            }
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

            for (shape shape : shapes) {
                  if(shape.getIfSelected(x,y)) {
                        return true;
                  }
            }

            return false;
      }

      public void setRootFalse() {
            this.root = false;
      }

      @Override
      public void mouseDragged(MouseEvent e) {
//            if(selected && dragging) {
//                  this.x = e.getX();
//                  this.y = e.getY();
//                  repaint();
//            }


            if(selected && dragging) {
                  this.x = e.getX();
                  this.y = e.getY();
                  repaint();
                  if(!shapes.isEmpty()) {
                        System.out.println("Not empty shapes");
                        int i = 0;
                        for (shape shape : shapes) {
                              //shape.x = (this.x + (shape.x - this.x));
                              //shape.y = (this.y + (shape.y - this.y));
                              shape.x = this.x + (savedChildX.get(i) - savedRootX);
                              shape.y = this.y + (savedChildY.get(i) - savedRootY);
                              i += 1;
                              //shape.y = e.getY() + shape.y;
                              repaint();
                        }

                  }

            }


      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }

      public void addToGroup(shape shape) {
            System.out.println("Not empty shapes");
            this.shapes.add(shape);
      }

      public boolean isRoot() {
            return root;
      }
}