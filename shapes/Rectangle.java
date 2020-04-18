package shapes;

import java.awt.Graphics;
import java.awt.Color;

public class Rectangle extends Shape {
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
}