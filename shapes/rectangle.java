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
            if(this.drawed){
                  if(this.selected){
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(4, 4, getWidth() - 8, getHeight() - 8);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
                  } else {
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(4, 4, getWidth() - 8, getHeight() - 8);
                  }
            }
      }
}