package shapes;

import java.awt.*;

import javax.swing.JComponent;

import visitor.Visitor;

public class Rectangle extends BaseShape {
      // public int x;
      // public int y;
      // public int width;
      // public int height;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void print(){
            System.out.println("rectangle");
      }

      public void accept(Visitor visitor){
            // visitor.visitRectangle(this);
      }

      @Override
      public void paintComponent(Graphics g) {
            if(this.selected){
                  super.paintComponent(g);
                  g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                  g.fillRect(this.x, this.y, this.width, this.height);
                  g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                  g.drawRect(this.x, this.y, this.width, this.height);

                  g.setColor(Color.WHITE);
                  g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);

                  g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                  g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);

                  
            } else {
                  super.paintComponent(g);
                  g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                  g.fillRect(this.x, this.y, this.width, this.height);
            }
      }
}