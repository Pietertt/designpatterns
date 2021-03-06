package shapes;

import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.Line;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import strategies.Strategy;
import UI.*;
import visitor.Visitor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public abstract class BaseShape extends JComponent {
      // The coordinates
      public int x;
      public int y;
      public int width;
      public int height;

      // The undo and redo stack
      public Stack<Location> redoStack = new Stack<Location>();
      public Stack<Location> undoStack = new Stack<Location>();

      public Location start = null;

      // Some variables defining the current state
      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      // The strategy which defines some behaviour
      public Strategy strategy;

      // Any children
      public ArrayList<BaseShape> children = new ArrayList<BaseShape>();

      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public BaseShape(){

      }

      public abstract void setStrategy(Strategy strategy);
      public abstract void place();
      public abstract void remove();
      public abstract void dragCommand(Location location);      
      public abstract void resizeCommand(Location location);
      public abstract void undoDrag();
      public abstract void redoDrag();
      public abstract void resize(Location location);
      public abstract void drag(Location location);
      public abstract void clear();
      public abstract void print(Layers layers);
      public abstract void select(MouseEvent e);
      public abstract void deselect(MouseEvent e);
      public abstract boolean getIfSelected(int x, int y);
      public abstract void accept(Visitor visitor);

      public abstract String toString(int indent);

      public void save(Location location){
            // Clears the redostack and adds a new element to the undostack
            this.redoStack.clear();
            this.undoStack.add(location);
            // Sets the starting location
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public abstract boolean getHandleIfSelected(int x, int y);
}