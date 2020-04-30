package shapes;

import UI.Invoker;
import visitor.Visitor;
import UI.Board;
import javax.swing.JComponent;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Graphic {
      public void accept(Visitor visitor);
      public void place(Invoker invoker, Board board);
      public void remove();
      public void select(MouseEvent e);
      public void deselect();
      public void drag(Location location);
      public void undoDrag();
      public void redoDrag();
}