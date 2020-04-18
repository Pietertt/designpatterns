package shapes;

import javax.swing.JPanel;
import java.awt.Graphics;

public abstract class Shape extends JPanel {
      public int x;
      public int y;
      public int width;
      public int height;

      public boolean selected = false;
      public boolean dragging = false;

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

      public abstract void drag(int x, int y);
}