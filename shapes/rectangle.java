package shapes;

import javax.swing.*;

import java.awt.*;

public class rectangle extends shape {

      public int width;
      public int height;
      public boolean selected = false;
      public boolean pressed = false;
      public int id;

      public rectangle(int x, int y, int width, int height, int id, int[] rgb){
            super(x, y, rgb);
            this.width = width;
            this.height = height;
            this.id = id;
      }
}