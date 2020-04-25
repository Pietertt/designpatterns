package shapes;

import java.awt.*;

public class Ellipse extends Shape {

      public Ellipse(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(0, 0, this.width, this.height);
      }
}