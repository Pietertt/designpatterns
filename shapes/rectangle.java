package shapes;

import java.awt.*;
import visitor.Visitor;

public class Rectangle extends Shape {

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      // Accepts any visitor and passes the current object to it
      public void accept(Visitor visitor){
            visitor.visitRectangle(this);
      }

      @Override
      public void paintComponent(Graphics g) {
            // The drawed variable must be set to true
            if(this.drawed){
                  // Draws a border when the shape is selected
                  if(this.selected){
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(4, 4, getWidth() - 8, getHeight() - 8);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
                  // Draws only the shape when the shape isn't selected
                  } else {
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(4, 4, getWidth() - 8, getHeight() - 8);
                  }
            }
      }
}