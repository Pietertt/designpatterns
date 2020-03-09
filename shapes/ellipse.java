package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ellipse extends shape {

      public int width;
      public int height;

      public ellipse(int x, int y, int width, int height, int[] rgb){
            super(x, y, width, height, rgb);
            this.width = width;
            this.height = height;
      }
}