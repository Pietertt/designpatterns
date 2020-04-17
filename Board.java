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
      private JFrame frame;

      public ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();

      public Board(JFrame frame){
            this.frame = frame;
      }

      public void mouseClicked(MouseEvent e){
            Rectangle rect = new Rectangle(0, 0, 100, 100);
            rect.addMouseListener(this);
            rect.setFocusable(true);
            this.shapes.add(rect);
            this.frame.add(rect);
            repaint();
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