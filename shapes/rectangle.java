package shapes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Rectangle extends JPanel {
      private int x;
      private int y;
      private int width;
      private int height;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void draw(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}