package shapes;

import javax.swing.*;

public class shape extends JPanel {
      public int x;
      public int y;
      public int width;
      public int height;

      public int[] color;

      public boolean moving = false;
      public boolean selected = false;

      public shape(int x, int y, int width, int height, int[] color){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
      }

      public boolean selected(int x, int y){
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
}