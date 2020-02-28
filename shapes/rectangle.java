package shapes;

import javax.swing.*;

import java.awt.*;

public class rectangle extends shape {

      public int width;
      public int height;
      public int id;

      public rectangle(int x, int y, int width, int height, int id, int[] rgb){
            super(x, y, rgb);
            this.width = width;
            this.height = height;
            this.id = id;
      }

      public void resize(int offsetX, int offsetY, int x, int y){
            this.width = (x - offsetX) - this.x;
            this.height = (y - offsetY) - this.y;
      }     
}