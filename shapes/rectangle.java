package shapes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;

public class Rectangle extends JPanel {
      private int x;
      private int y;
      private int width;
      private int height;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            this.addMouseListener(new MouseAdapter() {
                  public void mousePressed(MouseEvent e) {
                        moveSquare(e.getX(), e.getY());
                  }
            });

            this.addMouseMotionListener(new MouseAdapter() {
                  public void mouseDragged(MouseEvent e) {
                        moveSquare(e.getX(), e.getY());
                  }
            });
      }

      private void moveSquare(int x, int y) {
            int offset = 1;
            if ((this.x != x) || (this.y != y)) {
                  repaint(this.x, this.y, this.width + offset, this.height + offset);
                  this.x = x;
                  this.y = y;
                  repaint(this.x, this.y, this.width + offset, this.height + offset);
            }
      }

      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(this.x, this.y, this.width, this.height);
      }
}