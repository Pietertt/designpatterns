package shapes;

import javax.swing.*;

import java.util.ArrayList;

public class shape extends JPanel {
      public int x;
      public int y;
      public int width;
      public int height;

      public int[] color;

      public ArrayList<handle> handles  = new ArrayList<handle>();

      public shape(int x, int y, int width, int height, int[] color){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
      }

      public void select(){
            System.out.println("Selecting this rectangle");
      }
      

}