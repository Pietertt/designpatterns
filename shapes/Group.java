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

public class Group extends BaseShape {
      private ArrayList<BaseShape> children = new ArrayList<BaseShape>();
      public Board board;

      public Group(int x, int y, int width, int height, Board board){
            super(x, y, width, height);
            this.board = board;
      }

      public void addd(BaseShape shape){
            this.children.add(shape);
      }

      public void remove(Shape shape){
            this.children.remove(shape);
      }

      public void accept(Visitor visitor){

      }

      public void print(){
            
            // for(BaseShape shape : this.children){
            //       shape.print();
            // }
      }

      public void place() {
            this.drawed = true;
            repaint();
      }

      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void move(Location location){
            for(BaseShape shape : this.children){
                  Location childLocation = new Location();
                  childLocation.x = (shape.x - this.x) + location.x;
                  childLocation.y = (shape.y - this.y) + location.y;
                  childLocation.width = shape.width;
                  childLocation.height = shape.height;
                  shape.move(childLocation);
            }

            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void select() {
            this.selected = true;
            repaint();

            for(BaseShape shape : this.children){
                  Order select = new SelectShapeCommand(shape);
                  board.invoker.execute(select);
            }
      }

      public void deselect() {
            this.selected = false;
            repaint();

            for(BaseShape shape : this.children){
                  Order deselect = new DeselectShapeCommand(shape);
                  board.invoker.execute(deselect);
            }
      }

      public void drag(Location location){
            for(BaseShape shape : this.children){
                  Location childLocation = new Location(shape.x, shape.y, shape.width, shape.height);
                  Order drag = new DragShapeCommand(shape, childLocation);
                  this.board.invoker.execute(drag);
            }

            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void undoDrag() {
            for(BaseShape shape : this.children){
                  shape.undoDrag();
            }

            Location location = this.undoStack.pop();
            this.redoStack.add(location);
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  Location location = this.redoStack.pop();
                  this.undoStack.add(location);
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(this.drawed){
                  if(this.selected){
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(this.x, this.y, this.width, this.height);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawRect(this.x, this.y, this.width, this.height);
      
                        g.setColor(Color.WHITE);
                        g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);
      
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
                  } else {
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(this.x, this.y, this.width, this.height);
                  }
            }
      }
}