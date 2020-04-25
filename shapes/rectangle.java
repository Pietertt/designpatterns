package shapes;

import java.awt.*;

public class Rectangle extends Shape {

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      @Override
      public void paintComponent(Graphics g) {
            if(hasFocus()){
                  super.paintComponent(g);
                  g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                  g.fillRect(4, 4, this.width - 8, this.height - 8);
                  g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                  g.drawRect(0, 0, this.width, this.height);
            } else {
                  super.paintComponent(g);
                  g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                  g.fillRect(4, 4, this.width - 8, this.height - 8);
            }
      }
}