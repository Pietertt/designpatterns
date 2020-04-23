package shapes;

import javax.swing.*;

import java.util.ArrayList;

public class shape extends JComponent {
      public int x;
      public int y;
      public int width;
      public int height;

      public shape(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void select(){
            System.out.println("Selecting this rectangle");
      }

}