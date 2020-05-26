package UI;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import shapes.*;
import commands.*;
import io.Parser;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
      // Het frame waar het board deel van uitmaakt
      public JFrame frame;

      // Invoker van het command pattern wordt het handlen van execute/redo/uno
      public Invoker invoker = new Invoker();

      // Alle shapes worden opgeslagen in de array
      public ArrayList<Shape> shapes = new ArrayList<Shape>();

      // Is de de rectangle/ellipse button geklikt?
      public boolean created = false;

      public String placeWhich = "";

      public Board(JFrame frame){
            super(null);
            this.frame = frame;
            addMouseListener(this);
            super.setFocusable(true);


            // FileIO, wordt ingeladen
            Parser parser = new Parser();
            ArrayList<String> data = parser.read("io/data.txt");
            ArrayList<Shape> shapes = parser.get(data);

            for(Shape shape : shapes){

                  Order place = new PlaceShapeCommand(shape, this.invoker, this);
                  this.invoker.execute(place);

                  add(place.shape);

                  this.shapes.add(place.shape);
            }
      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(500, 500);
      }

      // Rectangle/Ellipse aangemaakt op het board met een muisklik
      public void mouseClicked(MouseEvent e){
            if(this.created){
                  Shape shape = null;
                  if(placeWhich.equals("Rectangle")) {
                        shape = new Rectangle(e.getX(), e.getY(), 50, 50);
                  }
                  if(placeWhich.equals("Ellipse")) {
                        shape = new Ellipse(e.getX(), e.getY(), 50, 50);
                  }

                  Order place = new PlaceShapeCommand(shape, this.invoker, this);
                  this.invoker.execute(place);

                  add(place.shape);

                  this.shapes.add(place.shape);

                  revalidate();
                  repaint();
                  this.created = false;
            }
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      // Als de muis wordt losgelaten wordt de shape gedeselecteerd
      public void mousePressed(MouseEvent e){
            for(Shape shape : this.shapes){
                  if(shape.selected){
                        // Deselecteren wordt aangeroepen aan de hand van command pattern
                        Order deselect = new DeselectShapeCommand(shape, e);
                        this.invoker.execute(deselect);      
                        requestFocus();                
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