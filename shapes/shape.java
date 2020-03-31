package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import shapes.Shape;

public class Shape extends JPanel {
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

      public void place(){
            repaint();
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println("Drawed");
            g.setColor(Color.BLACK);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}