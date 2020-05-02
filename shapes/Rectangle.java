package shapes;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import visitor.Visitor;

public class Rectangle extends BaseShape {
      public Handle handle;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            
            this.handle = new Handle(this.x + this.width, this.y + this.height, 12, 12);
      }

      public void print(){
            System.out.println("rectangle");
      }

      public void accept(Visitor visitor){
            // visitor.visitRectangle(this);
      }

      public boolean getHandleIfSelected(int x, int y){
            for(int i = this.handle.x; i < this.handle.x + this.handle.width; i++){
                  for(int j = this.handle.y; j < this.handle.y + this.handle.height; j++){
                        if(i == x){
                              if(j == y){
                                    return true;
                              }
                        }
                  }
            }
            return false;
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
                  g.fillOval(this.handle.x, this.handle.y, this.handle.width, this.handle.height);

                  // g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                  // g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);

                  
            } else {
                  super.paintComponent(g);
                  g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                  g.fillRect(this.x, this.y, this.width, this.height);
            }
      }
}