package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import commands.*;
import shapes.*;
import strategies.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();
      public ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();
      public Strategy strategy;

      public boolean created = false;

      public Board(JFrame frame){        
            setOpaque(false);    
            super.setFocusable(true);
            this.frame = frame;
            addMouseListener(this);
            addMouseMotionListener(this);
      }

      public void init(){
            this.strategy = new PlaceRectangleStrategy(this.invoker, this);

            for(int i = 0; i < 5; i++){
                  this.strategy.place(50 + i * 100, 100, 50, 50);
                  this.shapes.add(this.strategy.shape);
                  this.frame.add(this.strategy.shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            this.strategy = new PlaceEllipseStrategy(this.invoker, this);

            for(int i = 0; i < 5; i++){
                  this.strategy.place(50 + i * 100, 300, 50, 50);
                  this.shapes.add(this.strategy.shape);
                  this.frame.add(this.strategy.shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            Group group = new Group(100, 400, 50, 50, this);
            group.place();

            this.strategy.place(200, 400, 50, 50);
            group.addd(this.strategy.shape);
            this.frame.add(this.strategy.shape);
            this.frame.revalidate();
            this.frame.repaint();

            this.strategy.place(300, 400, 50, 50);
            group.addd(this.strategy.shape);
            this.frame.add(this.strategy.shape);
            this.frame.revalidate();
            this.frame.repaint();

            this.shapes.add(group);
            this.frame.add(group);
            this.frame.revalidate();
            this.frame.repaint();
      }

      public void mousePressed(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.drawed){
                        if(shape.getIfSelected(e.getX(), e.getY())){
                              Order select = new SelectShapeCommand(shape);
                              this.invoker.execute(select);
                        }
      
                        if(shape.selected){
                              if(shape.getIfSelected(e.getX(), e.getY())){
                                    Order drag = new DragShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                                    this.invoker.execute(drag);
                              } else {
                                    if(shape.getHandleIfSelected(e.getX(), e.getY())){
                                          Order resize = new ResizeShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                                          this.invoker.execute(resize);
                                    } else {
                                          Order deselect = new DeselectShapeCommand(shape);
                                          this.invoker.execute(deselect);
                                    }
                              }
                        }
                  }
            }
      }

      public void mouseReleased(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.dragging){
                        shape.dragging = false;
                  }

                  if(shape.resizing){
                        shape.resizing = false;
                  }
            }
      }

      public void mouseEntered(MouseEvent e){
            
      }

      public void mouseExited(MouseEvent e){
            
      }

      public void mouseClicked(MouseEvent e){
            
      }

      @Override
      public void mouseMoved(MouseEvent e){

      }

      @Override
      public void mouseDragged(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.resizing){
                        shape.width = e.getX() - shape.start.x;
                        shape.height = e.getY() - shape.start.y;
                        shape.repaint();
                  }

                  if(shape.dragging){
                        shape.x = e.getX();
                        shape.y = e.getY();
                        shape.repaint();
                  }
            }
      }



}