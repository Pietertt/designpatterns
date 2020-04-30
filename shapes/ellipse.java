package shapes;

import java.awt.*;

import visitor.Visitor;

public class Ellipse extends BaseShape {

      public Ellipse(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public String print(){
            return "ellipse";
      }

      public void accept(Visitor visitor){
            visitor.visitEllipse(this);
      }

      @Override
      public void paintComponent(Graphics g) {
            if(this.drawed){
                  if(this.selected){
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawOval(2, 2, getWidth() - 4, getHeight() - 4);
                  } else {
                        super.paintComponent(g);
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
                  }
            }
      }
}