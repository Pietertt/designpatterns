package shapes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Shape extends JPanel {
      public int x = 50;
      public int y = 50;
      public int width = 20;
      public int height = 20;

      public Shape(int x, int y, int width, int height){
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

      public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}