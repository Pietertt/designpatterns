package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ellipse extends shape {

      public int width;
      public int height;

      public ellipse(int x, int y, int width, int height){
            super(x, y);
            this.width = width;
            this.height = height;
      }
}