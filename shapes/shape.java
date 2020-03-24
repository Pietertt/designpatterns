package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import shapes.Shape;

public class Shape {
      public int x;
      public int y;
      public int width;
      public int height;

      public int[] color;

      public Shape(int x, int y, int width, int height, int[] color){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
      }

      public void draw(ArrayList<Shape> s){
            
      }
}