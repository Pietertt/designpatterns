package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import shapes.Rectangle;

import java.awt.event.*;

public class Board extends JPanel implements MouseListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();

      public Board(JFrame frame){            
            super.setFocusable(true);
            this.frame = frame;
            addMouseListener(this);
      }

      public void init(){
            
      }

      public void mousePressed(MouseEvent e){
            Rectangle rectangle = new Rectangle(100, 100, 100, 100);
            this.frame.add(rectangle);
            this.frame.revalidate();
            this.frame.repaint();
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