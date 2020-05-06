package shapes;

import java.awt.event.*;
import UI.*;
import visitor.Visitor;

public interface Shape {
      // public void accept(Visitor visitor);
      // public void print();
      public void place();
      public void remove();
      public void select(MouseEvent e);
      public void deselect(MouseEvent e);
      public void drag(Location location);
      public void resize(Location location);
      public void undoDrag();
      public void redoDrag();
}