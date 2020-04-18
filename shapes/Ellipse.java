package shapes;

import java.awt.Graphics;
import java.awt.Color;

public class Ellipse extends Shape {
      public Ellipse(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void drag(int x, int y){
            
      }

      public void draw(Graphics g){
           if(this.selected){
                  g.setColor(Color.RED);
                  g.fillOval(this.x - 2, this.y - 2, this.width + 4, this.height + 4);
                  g.setColor(Color.BLUE);
                  g.fillOval(this.x, this.y, this.width, this.height);
           } else {
                  g.setColor(Color.BLUE);
                  g.fillOval(this.x, this.y, this.width, this.height);
           }
      }
}