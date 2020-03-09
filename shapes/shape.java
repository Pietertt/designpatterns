package shapes;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

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
}