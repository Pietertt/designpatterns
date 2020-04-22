package shapes;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;

public class Rectangle extends JComponent {
      public int x;
      public int y;
      public int width;
      public int height;

      public boolean selected = false;
      public boolean dragging = false;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public boolean getIfSelected(int x, int y){
            for(int i = 0; i < this.width; i++){
                  if(x == this.x + i){
                        for(int j = 0; j < this.height; j++){
                              if(y == this.y + j){
                                    return true;
                              }
                        }
                  }
            }

            return false;
      }

      public void select() {
            this.selected = true;
      }

      public void deselect() {
            this.selected = false;
      }

      public void drag(){
            if(this.selected){
                  this.dragging = true;
            }
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