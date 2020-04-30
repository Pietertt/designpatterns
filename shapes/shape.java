package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
import commands.*;
import UI.*;
import visitor.Visitor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;

public interface Shape {
      public void accept(Visitor visitor);
      public String print();
      public void place(Invoker invoker, Board board);
      public void remove();
      public void select(MouseEvent e);
      public void deselect();
      public void drag(Location location);
      public void undoDrag();
      public void redoDrag();
}