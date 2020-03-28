package ui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import shapes.*;

import commands.*;

public class board extends JPanel implements MouseListener {

      private static JFrame frame;
      private static ui ui;

      private ArrayList history = new ArrayList();

      private Stack<order> ordersToExectute = new Stack<>();

      private order order;

      // worden de commando's naar verstuurd:
      private commandInvoker commandInvoker = new commandInvoker();

      

      // //-----------------------------------------------------------------------------
      // // colors
      // //-----------------------------------------------------------------------------

      public static int[] GRAY = { 213, 213, 213 };
      public static int[] BLUE = { 76, 153, 229 };

      // //-----------------------------------------------------------------------------
      // // Modes
      // //-----------------------------------------------------------------------------

      public int mode = 0;
      public String kind = "";

      public boolean dragging = false;
      public boolean added = false;

      public static int offsetX = 0;
      public static int offsetY = 0;
      public static int width = 600;
      public static int height = 600;

      public board(JFrame frame) {
            super.setFocusable(true);
            addMouseListener(this);
            this.frame = frame;
      }

      public void undo() {
            this.commandInvoker.undo();
      }

      public void redo() {
            this.commandInvoker.redo();
      }

      // paint method which is responsible for painting the window
      @Override
      public void paintComponent(Graphics g) {

      }

      public void update() {

      }   
      
      public void mouseClicked(MouseEvent e){
            //order.execute();
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();

            rectangle rc = new rectangle(x - 30, y - 50,50,50, 1,GRAY);

            placeShapeCommand place = new placeShapeCommand(rc);

            this.commandInvoker.execute(place);
            frame.add(place.getShape());
            frame.revalidate();
            frame.repaint();
            //}
      }

      public void mousePressed(MouseEvent e){

      }
}