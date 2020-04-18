package shapes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;

public class Rectangle extends Shape implements MouseMotionListener {
      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void drag(int x, int y){
            System.out.println("MOve");
      }

      public void draw(Graphics g) {
            super.paintComponent(g);
            if (this.selected) {
                  g.setColor(Color.RED);
                  g.fillRect(this.x - 2, this.y - 2, this.width + 4, this.height + 4);
                  g.setColor(Color.BLUE);
                  g.fillRect(this.x, this.y, this.width, this.height);
            } else {
                  g.setColor(Color.BLUE);
                  g.fillRect(this.x, this.y, this.width, this.height);
            }
      }

      @Override
      public void mouseDragged(MouseEvent e) {
            System.out.println("Dragging");
      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }
}