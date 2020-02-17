package shapes;

import javax.swing.*;
import java.awt.*;

public class rectangle extends shape {

      public int width;
      public int height;

      public rectangle(int x, int y, int width, int height){
            super(x, y);
            this.width = width;
            this.height = height;
      }
}