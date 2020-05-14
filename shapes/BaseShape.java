package shapes;

import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.Line;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import UI.*;
import visitor.Visitor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public abstract class BaseShape extends JComponent implements Shape {
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

      public BaseShape(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public abstract void place();
      public abstract void remove();
      public abstract void drag(Location location);
      public abstract void undoDrag();
      public abstract void redoDrag();
      public abstract void move(Location location);
      public abstract void resize(Location location);
      public abstract void clear();
      public abstract void print(Layers layers);



       public abstract void accept(Visitor visitor);
      // public abstract void print();


      public abstract void select(MouseEvent e);

      public abstract void deselect(MouseEvent e);

      public abstract boolean getIfSelected(int x, int y);

      public void save(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
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