package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import shapes.*;
import commands.*;
import io.Parser;
import strategies.*;
import visitor.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
      public JFrame frame;
      public JPanel layers;
      public JLabel label = new JLabel("<html>");

      public Invoker invoker = new Invoker();
      public Strategy strategy;
      public ArrayList<Shape> shapes = new ArrayList<Shape>();

      public boolean created = false;

      public Board(JFrame frame, JPanel layers){
            super(null);
            this.frame = frame;
            this.layers = layers;
            this.layers.add(this.label);
            addMouseListener(this);
            super.setFocusable(true);

            Parser parser = new Parser();
            ArrayList<String> data = parser.read("io/data.txt");
            ArrayList<Shape> shapes = parser.get(data);

            for(Shape shape : shapes){
                  if(shape instanceof Rectangle){
                        this.strategy = new PlaceRectangleStrategy(this.invoker, this);  
                  }

                  if(shape instanceof Ellipse){
                        this.strategy = new PlaceEllipseStrategy(this.invoker, this);  
                  }

                  Visitor move = new moveVisitor();
                  Visitor resize = new resizeVisitor();

                  this.strategy.place(shape.x, shape.y, shape.width, shape.height);
                  this.strategy.shape.accept(move);
                  this.strategy.shape.accept(resize);

                  this.label.setText(label.getText() + this.strategy.shape.print() + "<br>");
                  
                  add(this.strategy.shape);
                  this.shapes.add(this.strategy.shape);
            }

            Graphic group = new Group();

            for(int i = 0; i < 3; i++){
                  Visitor move = new moveVisitor();
                  Visitor resize = new resizeVisitor();

                  this.strategy.place(i * 100, 400, 50, 50);
                  this.strategy.shape.accept(move);
                  this.strategy.shape.accept(resize);

                  this.label.setText(label.getText() + this.strategy.shape.print() + "<br>");
                  
                  add(this.strategy.shape);
                  //group.add(this.strategy.shape);
                  this.shapes.add(this.strategy.shape);
            }

            this.layers.revalidate();
            this.layers.repaint();

      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(500, 500);
      }

      public void mouseClicked(MouseEvent e){
            if(this.created){
                  Visitor move = new moveVisitor();
                  Visitor resize = new resizeVisitor();

                  this.strategy.place(e.getX(), e.getY(), 50, 50);
                  this.strategy.shape.accept(move);
                  this.strategy.shape.accept(resize);

                  this.label.setText(label.getText() + this.strategy.shape.print() + "<br>");
                  this.layers.revalidate();
                  this.layers.repaint();

                  add(this.strategy.shape);
                  this.shapes.add(this.strategy.shape);

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

      public void mousePressed(MouseEvent e){
            for(Shape shape : this.shapes){
                  if(shape.selected){
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

      @Override
      public void paintComponent(Graphics g) {

      }
}