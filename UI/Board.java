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
import strategies.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();
      public Strategy strategy;
      public ArrayList<Shape> shapes = new ArrayList<Shape>();

      public boolean created = false;

      public Board(JFrame frame){
            super(null);
            this.frame = frame;
            addMouseListener(this);
            super.setFocusable(true);

            for(int i = 0; i < 5; i++){
                  this.strategy = new PlaceRectangleStrategy(this.invoker);
                  this.strategy.prepare(50 + i * 75, 200, 50, 50);
                  this.strategy.place();
                  add(this.strategy.shape);
                  this.shapes.add(this.strategy.shape);
            }
      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(500, 500);
      }

      public void mouseClicked(MouseEvent e){
            if(this.created){
                  this.strategy.prepare(e.getX(), e.getY(), 50, 50);
                  this.strategy.place();
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
                  if(shape.getIfSelected(e.getX(), e.getY())){
                        System.out.println("Click");
                        Order select = new SelectShapeCommand(shape, e);
                        this.invoker.execute(select);
                        shape.requestFocus();

                  } else {
                        if(shape.selected){
                              Order deselect = new DeselectShapeCommand(shape, e);
                              this.invoker.execute(deselect);      
                              requestFocus();                
                        }
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