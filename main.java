import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import shapes.rectangle;
import shapes.ellipse;

public class main extends JPanel {

      private ArrayList<rectangle> rects = new ArrayList<rectangle>();
      private ArrayList<ellipse> ellipses = new ArrayList<ellipse>();
      

      public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(new Color(212, 212, 212));

            for(int i = 0; i < rects.size(); i++){
                  g2d.fillRect(rects.get(i).x, rects.get(i).y, rects.get(i).width, rects.get(i).height);
            }

            for(int i = 0; i < ellipses.size(); i++){
                  g2d.fill(new Ellipse2D.Double(ellipses.get(i).x, ellipses.get(i).y, ellipses.get(i).width, ellipses.get(i).height));
            }

      }

  public static void main(String[] args) {
      main main = new main();

      JFrame frame = new JFrame();

      frame.add(main);

      for(int i = 0; i < 5; i++){
            main.rects.add(new rectangle(50 + i * 75, 50, 50, 50));
      }

      for(int i = 0; i < 5; i++){
            main.ellipses.add(new ellipse(50 + i * 75, 150, 50, 50));
      }
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 500);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
  }
}