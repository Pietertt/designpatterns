package shapes;

import java.awt.*;

import javax.swing.JComponent;

import visitor.Visitor;

public class Rectangle /*extends BaseShape*/ {
      // public int x;
      // public int y;
      // public int width;
      // public int height;

      // public Rectangle(int x, int y, int width, int height) {
      //       this.x = x;
      //       this.y = y;
      //       this.width = width;
      //       this.height = height;
      // }

      public void print(){
            System.out.println("rectangle");
      }

      public void accept(Visitor visitor){
            // visitor.visitRectangle(this);
      }

      // @Override
      // public void paintComponent(Graphics g) {
      //       if(this.selected){
      //             super.paintComponent(g);
      //             g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
      //             g.fillRect(this.x, this.y, this.width - 8, this.height - 8);
      //             g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
      //             g.drawRect(this.x, this.y, this.width - 4, this.height - 4);
      //       } else {
      //             super.paintComponent(g);
      //             g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
      //             g.fillRect(this.x, this.y, this.width - 8, this.height - 8);
      //       }
      // }
}