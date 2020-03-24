package ui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import shapes.Shape;
import shapes.Rectangle;
import shapes.ellipse;
import shapes.handle;

import commands.*;

public class board extends JPanel implements MouseListener {

      private static JFrame frame;
      private static ui ui;

      private Order order;

      private Stack<Order> history = new Stack<Order>();
      private ArrayList<Shape> shapes = new ArrayList<Shape>();

      

      //-----------------------------------------------------------------------------
      //                                  Colors
      //-----------------------------------------------------------------------------

      public static int[] GRAY = { 213, 213, 213 };
      public static int[] BLUE = { 76, 153, 229 };

      //-----------------------------------------------------------------------------
      //                                  Modes
      //-----------------------------------------------------------------------------

      public int mode = 0;
      public String kind = "";

      public boolean dragging = false;
      public boolean added = false;

      public static int offsetX = 0;
      public static int offsetY = 0;
      public static int width = 600;
      public static int height = 600;

      public board(JFrame frame) {
            this.frame = frame;
            super.setFocusable(true);
            addMouseListener(this);
      }

      // paint method which is responsible for painting the window
      @Override
      public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            for(int i = 0; i < this.shapes.size(); i++){
                  g2d.setColor(new Color(0, 0, 0));
                  g2d.fillRect(50 + (i * 50), 50, 50, 50);
            }
      }

      public ArrayList<Shape> update(ArrayList<Shape> s) {
            ArrayList<Shape> shapes = s;

            return s;
      }   
      
      public void mouseClicked(MouseEvent e){
            Shape rectangle = new Rectangle(100, 100, 50, 50, 0, this.GRAY);
            Order c = new createShapeCommand(rectangle, this.shapes);

      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      public void mousePressed(MouseEvent e){

      }
}