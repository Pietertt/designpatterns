package shapes;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class rectangular {
      private int width;
      private int height;
      private int x;
      private int y;

      public rectangular(int x, int y, int width, int height){
            super(x,y, width, height);
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.fillRect(this.x, this.y, 100, 100);
        }
}