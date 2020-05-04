package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import commands.*;
import shapes.*;

import java.awt.event.*;

public class Board extends JPanel implements MouseListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();

      public Board(JFrame frame){        
            setOpaque(false);    
            super.setFocusable(true);
            this.frame = frame;
            addMouseListener(this);
      }

      public void init(){
            for(int i = 0; i < 5; i++){
                  Rectangle rectangle = new Rectangle(50 + i * 100, 100, 50, 50);
                  Order place = new PlaceShapeCommand(rectangle);
                  this.invoker.execute(place);
                  this.frame.add(rectangle);
                  this.frame.revalidate();
                  this.frame.repaint();
            }
      }

      public void mousePressed(MouseEvent e){
            
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