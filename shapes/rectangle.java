package shapes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import java.awt.Dimension;

public class Rectangle extends JComponent {
      private int x;
      private int y;
      private int width;
      private int height;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      @Override
      public Dimension setPreferredSize() {
            return new Dimension(this.width, this.height);
      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(this.width, this.height);
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}