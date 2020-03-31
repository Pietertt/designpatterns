package ui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
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

      ArrayList<Command> commands = new ArrayList<Command>();
      ArrayList<Shape> shapes = new ArrayList<Shape>();

      public board(JFrame frame) {
            this.frame = frame;
            super.setFocusable(true);
            addMouseListener(this);
      }

      @Override
      public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
      }

      public ArrayList<Command> update(ArrayList<Command> commands) {
            this.commands = commands;

            for(int i = 0; i < commands.size(); i++){
                  commands.get(i).execute();
            }

            frame.repaint();

            return this.commands;
      }   
      
      public void mouseClicked(MouseEvent e){
            Shape rect = new Rectangle(100, 100, 100, 100, 5, this.BLUE);
            Command command = new createShapeCommand(rect);
            this.commands.add(command);
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