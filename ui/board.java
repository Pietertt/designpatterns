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

      public static int[] GRAY = { 213, 213, 213 };
      public static int[] BLUE = { 76, 153, 229 };

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
      }

      public void update() {

         
      }   
      
      public void mouseClicked(MouseEvent e){

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