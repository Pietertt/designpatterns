package ui;

import java.util.*;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;

import io.fileIO;
import shapes.*;
import strategies.*;

import commands.*;

public class board extends JPanel implements MouseListener, MouseMotionListener {

      private static JFrame frame;

      // worden de commando's naar verstuurd:
      public commandInvoker commandInvoker = new commandInvoker();
      public Strategy strategy;
      

      // Alle shapes die op de canvas(board) staan
      public ArrayList<shape> shapes;

      // Alle shapes die momenteel geselecteerd zijn
      public ArrayList<shape> selectedShapes;

      // MODUS
      public boolean created = false;
      public boolean drag_creating = false;
      private boolean selectionMode = false;
      private boolean mouseIsDragging = false;

      public static int offsetX = 0;
      public static int offsetY = 0;
      public static int width = 600;
      public static int height = 600;

      public board(JFrame frame) {
            shapes = new ArrayList<shape>();
            selectedShapes = new ArrayList<shape>();
            super.setFocusable(true);
            addMouseListener(this);
            this.frame = frame;
      }

      // paint method which is responsible for painting the window
      @Override
      public void paintComponent(Graphics g) {

      }

      public void saveFile() {
            fileIO.saveFile();
      }

      public void update() {

      }

      public void mouseClicked(MouseEvent e) {
            selectionMode = false;

            // Reset selectionMode when clicking in an empty area
            for (shape shape : shapes) {
                  if (shape.getIfSelected(e.getX(), e.getY())) {
                        selectionMode = true;
                  }
            }

            if(!selectionMode){
                  if(this.created){
                        if(!selectionMode) {
                              int x = e.getX();
                              int y = e.getY();
                
                              // this.commandInvoker.execute(place);
                              // frame.add(place.getShape());

                              this.strategy.prepare(x, y, 50, 50);
                              this.strategy.place();

                              shapes.add(this.strategy.shape);
                              frame.add(this.strategy.shape);


                              frame.revalidate();
                              frame.repaint();
            
                
                              addMouseMotionListener(this.strategy.shape);
                              this.created = false;
                          }
                  }
            }

      }

      public void mouseExited(MouseEvent e) {

      }

      public void mouseEntered(MouseEvent e) {
            // TODO In Progress: Change mouse when entering a shape
            for (shape shape : shapes) {
                  if (shape.getIfSelected(e.getX(), e.getY())) {
                        shape.setCursor(new Cursor(Cursor.HAND_CURSOR));
                  }
            }
      }

      public void mouseReleased(MouseEvent e) {
            // er kan niet gedragged worden als dragging false is.
            for (shape shape : shapes) {
                  shape.setDraggingFalse();
            }
      }

      public void mousePressed(MouseEvent e) {

            // If the shape is selected && the mouse is pressed you can drag a rectangle.
            for (shape shape : shapes) {
                  if (shape.getIfSelected(e.getX(), e.getY())) {
                        dragShapeCommand drag = new dragShapeCommand(shape);
                        this.commandInvoker.execute(drag);
                  }
            }

            if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                  for (shape shape : shapes) {
                        if (shape.getIfSelected(e.getX(), e.getY()) && !shape.getSelected()) {
                              selectShapeCommand select = new selectShapeCommand(shape);
                              this.commandInvoker.execute(select);
                              selectionMode = true;
                              selectedShapes.add(shape);
                        }
                  }
            } else {
                  // Check if every shape is selected
                  for (shape shape : shapes) {
                        if (shape.getIfSelected(e.getX(), e.getY())) {
                              selectedShapes.clear();
                              selectShapeCommand select = new selectShapeCommand(shape);
                              select.execute();
                              //this.commandInvoker.execute(select);
                              selectionMode = true;
                        } else if (shape.getSelected()) {
                              selectedShapes.clear();
                              deselectShapeCommand deselect = new deselectShapeCommand(shape);
                              deselect.execute();
                              //this.commandInvoker.execute(deselect);
                              // selectionMode = false;
                              // rectangle.setSelectedFalse();
                        }
                  }
            }



      }

      @Override
      public void mouseDragged(MouseEvent e) {
            System.out.println(e.getX());
      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }


      public void groupShapes() {
            for (int i = 1; i < selectedShapes.size(); i++) {
                  selectedShapes.get(0).addToGroup(selectedShapes.get(i));
            }
      }
}