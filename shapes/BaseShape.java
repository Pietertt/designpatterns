package shapes;

import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.util.Stack;
import commands.*;
import UI.*;
import visitor.Visitor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public abstract class BaseShape extends JComponent /*Shape*/ {
      public int x;
      public int y;
      public int width;
      public int height;

      /*
       * A class is used to store the x, y, width and height
       */
      public Stack<Location> redoStack = new Stack<Location>();
      public Stack<Location> undoStack = new Stack<Location>();

      public Location start = null;

      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public void place(){
            this.drawed = true;
            repaint();
      }

      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void drag(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void resize(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.resizing = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

     public void undoDrag() {
            Location location = this.undoStack.pop();
            this.redoStack.add(location);
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  Location location = this.redoStack.pop();
                  this.undoStack.add(location);
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }
      }

      // public abstract void accept(Visitor visitor);
      // public abstract void print();


      public void select(MouseEvent e) {
            this.selected = true;
            repaint();
      }

      public void deselect() {
            this.selected = false;
            this.setBorder(BorderFactory.createEmptyBorder());
            repaint();
      }

      public boolean getIfSelected(int x, int y) {
            for (int i = 0; i < this.width; i++) {
                  if (x == this.x + i) {
                        for (int j = 0; j < this.height; j++) {
                              if (y == this.y + j) {
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      public boolean getHandleIfSelected(int x, int y){
            for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
                  for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
                        if(i == x){
                              if(j == y){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }
}