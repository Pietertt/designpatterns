package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import UI.*;
import shapes.*;
import visitor.Visitor;

public class Group extends JComponent implements Graphic {
      private ArrayList<Graphic> children = new ArrayList<Graphic>();

      public void add(Graphic graphic){
            this.children.add(graphic);
      }

      public void remove(Graphic graphic){
            this.children.remove(graphic);
      }

      public void place(Invoker invoker, Board board) {
            
      }

      public void remove() {
            
      }

      public void select(MouseEvent e) {
            
      }

      public void deselect() {
            
      }

      public void drag(Location location) {
           
      }

      public void undoDrag() {
           
      }

      public void redoDrag() {
            
      }
}