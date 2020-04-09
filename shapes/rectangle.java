package shapes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;

public class Rectangle extends JPanel {

      public Rectangle() {

      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(300, 300);
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillRect(100, 100, 100, 100);
      }
}