package shapes;

import javax.swing.JPanel;
import java.awt.Graphics;

public abstract class Shape extends JPanel {
      public int x;
      public int y;
      public int width;
      public int height;

      public boolean selected = false;

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

      public abstract void draw(Graphics g);
      public abstract void select();
      public abstract void deselect();
}