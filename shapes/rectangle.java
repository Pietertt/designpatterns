package shapes;

import java.awt.*;

public class rectangle extends shape implements receiver {

      public int id;
      public int[] color;

      // Moet de rectangle getekend worden
      private boolean rectDraw = false;

      public rectangle(int x, int y, int width, int height, int id, int[] rgb){
            super(x, y, width, height, rgb);
            this.width = width;
            this.height = height;
            this.id = id;
            this.color = rgb;
      }

      @Override
      public void paintComponent(Graphics g) {
            if(rectDraw) {
                  super.paintComponent(g);

                  Color c = new Color(1.0F, 0.0F, 1.0F);
                  g.setColor(c);

                  g.drawRect(x, y, width, height);
            }
      }

      public void setDrawTrue() {
            rectDraw = true;
            repaint();
      }

      public void setDrawFalse() {
            rectDraw = false;
            repaint();
      }
}