package shapes;

import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
import UI.*;

// Een basisfiguur waarvan anders figuren afgeleid kunnen worden
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

      // Welke opties/modus staan aan op deze shape
      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;
      public boolean handle = false;

      // Standaar kleuren van een shape
      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public BaseShape(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      // Standaard functionaliteit voor shape
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

      // Opslaan van een shape op de stack
      public void save(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public abstract boolean getHandleIfSelected(int x, int y);
}