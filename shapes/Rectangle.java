package shapes;

import java.awt.Graphics;
import java.awt.Color;

public class Rectangle extends Shape {

      public Rectangle(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void draw(Graphics g){
            g.setColor(Color.RED);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}