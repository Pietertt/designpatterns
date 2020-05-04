package shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import javax.swing.JComponent;

import visitor.Visitor;
import shapes.Location;

import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.util.Stack;
import commands.*;
import UI.*;
import visitor.Visitor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Rectangle extends JComponent {
      public int x;
      public int y;
      public int width;
      public int height;

      /*
       * A class is used to store the x, y, width and height
       */
      public Stack<Location> redoStack = new Stack<Location>();
      public Stack<Location> undoStack = new Stack<Location>();

      public int cursor;
      public Location start = null;
      public Invoker invoker;
      public Board board;
      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            //setLayout(new FlowLayout());  
      }

//       public void print(){
//             System.out.println("rectangle");
//       }

//       public void accept(Visitor visitor){
//             // visitor.visitRectangle(this);
//       }

//       public void drag(Location location){
//             this.redoStack.clear();
//             this.undoStack.add(location);
//             this.dragging = true;
//             this.start = new Location(location.x, location.y, location.width, location.height);
//             repaint();
//       }

//       public void resize(Location location){
//             this.redoStack.clear();
//             this.undoStack.add(location);
//             this.resizing = true;
//             this.start = new Location(location.x, location.y, location.width, location.height);
//             repaint();
//       }

//      public void undoDrag() {
//             Location location = this.undoStack.pop();
//             this.redoStack.add(location);
//             this.x = location.x;
//             this.y = location.y;
//             this.width = location.width;
//             this.height = location.height;
//             repaint();
//       }

//       public void redoDrag() {
//             if (this.redoStack.size() > 0) {
//                   Location location = this.redoStack.pop();
//                   this.undoStack.add(location);
//                   this.x = location.x;
//                   this.y = location.y;
//                   this.width = location.width;
//                   this.height = location.height;
//                   repaint();
//             }
//       }

      // public boolean getHandleIfSelected(int x, int y){
      //       for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
      //             for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
      //                   if(i == x){
      //                         if(j == y){
      //                               return true;
      //                         }
      //                   }
      //             }
      //       }
      //       return false;
      // }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            System.out.println("Drawed");
            g.fillRect(100, 100, 100, 100);
            // if(this.selected){
            //       super.paintComponent(g);
            //       g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
            //       g.fillRect(this.x, this.y, this.width, this.height);
            //       g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
            //       g.drawRect(this.x, this.y, this.width, this.height);

            //       g.setColor(Color.WHITE);
            //       g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);

            //       g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
            //       g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
            // } else {
            //       super.paintComponent(g);
            //       g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
            //       g.fillRect(this.x, this.y, this.width, this.height);
            // }
      }
}