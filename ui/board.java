package ui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import shapes.*;

import commands.*;

public class board extends JPanel implements MouseListener, MouseMotionListener {

      private static JFrame frame;

      // worden de commando's naar verstuurd:
      public commandInvoker commandInvoker = new commandInvoker();

      // Alle shapes die op de canvas(board) staan
      private ArrayList<rectangle> shapes = new ArrayList<>();

      // MODUS
      private boolean selectionMode = false;
      private boolean mouseIsDragging = false;

      public static int offsetX = 0;
      public static int offsetY = 0;
      public static int width = 600;
      public static int height = 600;

      public board(JFrame frame) {
            super.setFocusable(true);
            addMouseListener(this);
            this.frame = frame;
      }

      // paint method which is responsible for painting the window
      @Override
      public void paintComponent(Graphics g) {

      }

      public void update() {

      }   
      
      public void mouseClicked(MouseEvent e) {

          // Reset selectionMode when clicking in an empty area
          for(rectangle rectangle : shapes) {
              if (selectionMode && !rectangle.getIfSelected(e.getX(), e.getY())) {
                  selectionMode = false;
              }
          }

      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){
          // TODO In Progress: Change mouse when entering a shape
          for(rectangle rectangle : shapes) {
              if(rectangle.getIfSelected(e.getX(), e.getY())) {
                  rectangle.setCursor(new Cursor(Cursor.HAND_CURSOR));
              }
          }
      } 

      public void mouseReleased(MouseEvent e){
          // er kan niet gedragged worden als dragging false is.
          for(rectangle rectangle : shapes) {
              rectangle.setDraggingFalse();
          }

          // Place a shape if clicking in an empty area
          if(!selectionMode) {
              int x = e.getXOnScreen();
              int y = e.getYOnScreen();

              rectangle rc = new rectangle(x, y, 50, 50, 1);

              placeShapeCommand place = new placeShapeCommand(rc);

              this.commandInvoker.execute(place);
              frame.add(place.getShape());
              frame.revalidate();
              frame.repaint();

              shapes.add(place.getShape());

              addMouseMotionListener(place.getShape());
          }
      }

      public void mousePressed(MouseEvent e) {

          // If the shape is selected && the mouse is pressed you can drag a rectangle.
          for(rectangle rectangle : shapes) {
              if(rectangle.getIfSelected(e.getX(), e.getY())) {
                  dragShapeCommand drag = new dragShapeCommand(rectangle);
                  this.commandInvoker.execute(drag);
              }
          }


          // Check if every shape is selected
          for(rectangle rectangle : shapes) {
              if(rectangle.getIfSelected(e.getX(), e.getY())) {
                  selectShapeCommand select = new selectShapeCommand(rectangle);
                  this.commandInvoker.execute(select);
                  selectionMode = true;
              } else if(rectangle.getSelected())  {
                  deselectShapeCommand deselect = new deselectShapeCommand(rectangle);
                  this.commandInvoker.execute(deselect);
                  //selectionMode = false;
                  //rectangle.setSelectedFalse();
              }
          }


      }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}