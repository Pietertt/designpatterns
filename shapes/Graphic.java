package shapes;

import UI.Invoker;
import UI.Board;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Graphic {
      public void place(Invoker invoker, Board board);
      public void remove();
      public void select(MouseEvent e);
      public void deselect();
      public void drag(Location location);
      public void undoDrag();
      public void redoDrag();
}