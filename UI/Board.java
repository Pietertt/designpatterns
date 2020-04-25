package UI;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.*;

import shapes.*;
import commands.*;

public class Board extends JPanel implements MouseListener {
      public Board(){
            super(null);
            addMouseListener(this);
            setFocusable(true);

            Rectangle rectangle1 = new Rectangle(100, 100, 50, 50);
            add(rectangle1);

            Rectangle rectangle2 = new Rectangle(400, 100, 50, 50);
            add(rectangle2);
      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(500, 500);
      }

      public void mouseClicked(MouseEvent e){
            Rectangle rectangle = new Rectangle(e.getX(), e.getY(), 50, 50);
            Order place = new PlaceShapeCommand(rectangle);
            place.execute();
            add(place.shape);
            revalidate();
            repaint();
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      public void mousePressed(MouseEvent e){
            requestFocus();
                        
      }

      public void mouseDragged(MouseEvent e){

      }

      public void MouseMoved(MouseEvent e){

      }
}