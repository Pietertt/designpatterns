package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.*;

import shapes.*;
import commands.*;
import strategies.*;

public class Board extends JPanel implements MouseListener {
      public JFrame frame;
      public commandInvoker invoker = new commandInvoker();
      public Strategy strategy;

      public Board(JFrame frame){
            super(null);
            this.frame = frame;
            addMouseListener(this);
            setFocusable(true);
      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(500, 500);
      }

      public void mouseClicked(MouseEvent e){

            this.strategy.prepare(e.getX(), e.getY(), 50, 50);
            this.strategy.place();
            add(this.strategy.shape);

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