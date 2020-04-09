package shapes;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Rectangle extends JPanel {
      private int x;
      private int y;
      private int width;
      private int height;

      public Rectangle(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      public void place(){
            
      }

      @Override
      public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.fillRect(100, 100, 100, 100);
      }
}