package ui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import shapes.*;

import commands.*;

public class board extends JPanel implements MouseListener {

      private static JFrame frame;

      // worden de commando's naar verstuurd:
      private commandInvoker commandInvoker = new commandInvoker();

      // Alle shapes die op de canvas(board) staan
      private ArrayList<rectangle> shapes = new ArrayList<>();

      // MODUS
      private boolean selectionMode = false;

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

      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){
            if(!selectionMode) {
                  int x = e.getXOnScreen();
                  int y = e.getYOnScreen();

                  rectangle rc = new rectangle(x - 50, y - 50, 50, 50, 1);

                  placeShapeCommand place = new placeShapeCommand(rc);

                  this.commandInvoker.execute(place);
                  frame.add(place.getShape());
                  frame.revalidate();
                  frame.repaint();

                  shapes.add(place.getShape());
            }
      }

      public void mousePressed(MouseEvent e){
//          for(rectangle rectangle : shapes) {
//              Point p = MouseInfo.getPointerInfo().getLocation();
//              if(rectangle.contains(p)) {
//                  selectShapeCommand select = new selectShapeCommand(rectangle);
//                  this.commandInvoker.execute(select);
//                  selectionMode = true;
//              }
//          }


          for(rectangle rectangle : shapes) {
              if(rectangle.getIfSelected(e.getX(), e.getY())) {
                  selectShapeCommand select = new selectShapeCommand(rectangle);
                  this.commandInvoker.execute(select);
                  selectionMode = true;
              }
              else {

              }
          }
      }
}