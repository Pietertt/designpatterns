package shapes;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class shape extends JPanel {
      public int x;
      public int y;
      public int[] color;

      public boolean moving = false;
      public boolean selected = false;

      public shape(int x, int y, int[] color){
            this.x = x;
            this.y = y;
            this.color = color;
      }
}