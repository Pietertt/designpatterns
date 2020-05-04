package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import commands.*;
import shapes.*;

import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements MouseListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();
      public ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();

      public Board(JFrame frame){        
            setOpaque(false);    
            super.setFocusable(true);
            this.frame = frame;
            addMouseListener(this);
      }

      public void init(){
            for(int i = 0; i < 5; i++){
                  BaseShape rectangle = new Rectangle(50 + i * 100, 100, 50, 50);
                  Order place = new PlaceShapeCommand(rectangle);
                  this.invoker.execute(place);
                  this.shapes.add(rectangle);
                  this.frame.add(rectangle);
                  this.frame.revalidate();
                  this.frame.repaint();
            }
      }

      public void mousePressed(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.drawed){
                        if(shape.getIfSelected(e.getX(), e.getY())){
                              Order select = new SelectShapeCommand(shape, e);
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
                                          Order deselect = new DeselectShapeCommand(shape, e);
                                          this.invoker.execute(deselect);
                                    }
                              }
                        }
                  }
            }
      }

      public void mouseReleased(MouseEvent e){
            
      }

      public void mouseEntered(MouseEvent e){
            
      }

      public void mouseExited(MouseEvent e){
            
      }

      public void mouseClicked(MouseEvent e){
            
      }


}