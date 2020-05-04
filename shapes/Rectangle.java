package shapes;

import java.awt.*;

public class Rectangle extends BaseShape {

      public Rectangle(int x, int y, int width, int height) {
            super(x, y, width, height);
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(this.drawed){
                  if(this.selected){
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(this.x, this.y, this.width, this.height);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawRect(this.x, this.y, this.width, this.height);
      
                        g.setColor(Color.WHITE);
                        g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);
      
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
                  } else {
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(this.x, this.y, this.width, this.height);
                  }
            }
      }
}