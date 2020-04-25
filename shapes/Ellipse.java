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
            g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
            g.fillOval(4, 4, this.width - 8, this.height - 8);
      }
}