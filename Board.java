import shapes.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener {
      public ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();

      public Board(){
            setFocusable(true);
            addMouseListener(this);
      }

      public void mouseClicked(MouseEvent e){

      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      public void mousePressed(MouseEvent e){

      }

      public void mouseDragged(MouseEvent e){

      }

      public void MouseMoved(MouseEvent e){

      }

      public void paintComponent(Graphics g) {
            for(Rectangle rect : shapes){
                  rect.draw(g);
            }      
      }
}