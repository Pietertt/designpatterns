package shapes;

import java.awt.event.*;
import UI.*;

public interface Shape {
      // public void accept(Visitor visitor);
      // public void print();
      void place();
      void remove();
      void select(MouseEvent e);
      void deselect(MouseEvent e);
      void dragCommand(Location location);
      void resizeCommand(Location location);
      void resize(Location location);
      void drag(Location location);
      void undoDrag();
      void redoDrag();
      void print(Layers layers);
}