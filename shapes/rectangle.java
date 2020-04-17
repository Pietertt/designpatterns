package shapes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Rectangle extends JPanel implements MouseListener {
      private int x;
      private int y;
      private int width;
      private int height;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            addMouseListener(this);
            setFocusable(true);

      }

      public void mouseClicked(MouseEvent e){
            System.out.println("Cucj");
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

      public void draw(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}