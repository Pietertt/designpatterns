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

public abstract class Shape extends JComponent {
      public int x;
      public int y;
      public int width;
      public int height;

      /*
            A class is used to store the x, y, width and height
      */
      public Stack<Location> redoStack = new Stack<Location>();
      public Stack<Location> undoStack = new Stack<Location>();

      public int cursor;
      public Point start = null;
      public Invoker invoker;
      public Board board;

      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public abstract void accept(Visitor visitor);

      public void place(Invoker invoker, Board board){
            this.invoker = invoker;
            this.board = board;

            JPanel area = new JPanel();
            setOpaque(true);

            setBounds(this.x, this.y, this.width, this.height);
            add(area);
            this.drawed = true;
            repaint();
      }

      public void remove(){
            /*
                  Sets the drawed flag to false. 
                  The shape is hidden by doing this. Repaint is immediatly called to redraw the shape
            */           
            this.drawed = false;
            repaint();
      }

      public void select(MouseEvent e){
            this.selected = true;
            setBorder(new ResizableBorder());
            repaint();
      }

      public void deselect(){
            setBorder(BorderFactory.createEmptyBorder());
            this.selected = false;
            repaint();
      }

      public void drag(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
      }

      public void undoDrag(){
            Location location = this.undoStack.pop();
            this.redoStack.add(location);

            java.awt.Rectangle bounds = new java.awt.Rectangle();
            bounds.x = location.x;
            bounds.y = location.y;
            bounds.width = location.width;
            bounds.height = location.height;

            setBounds(bounds);
            repaint();
      }

      public void redoDrag(){
            if(this.redoStack.size() > 0){
                  Location location = this.redoStack.pop();
                  this.undoStack.add(location);

                  java.awt.Rectangle bounds = new java.awt.Rectangle();
                  bounds.x = location.x;
                  bounds.y = location.y;
                  bounds.width = location.width;
                  bounds.height = location.height;

                  setBounds(bounds);
                  repaint();
            }
      }

      public boolean getIfSelected(int x, int y) {
            for(int i = 0; i < this.width; i++){
                  if(x == this.x + i){
                        for(int j = 0; j < this.height; j++){
                              if(y == this.y + j){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }
}