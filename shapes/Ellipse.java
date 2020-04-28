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
            if(this.drawed){
                  if(this.selected){
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillOval(4, 4, this.width - 8, this.height - 8);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawOval(2, 2, this.width - 4, this.height - 4);
                  } else {
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillOval(4, 4, this.width - 8, this.height - 8);
                  }
            }
      }
}