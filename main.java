import java.util.ArrayList;

import java.util.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import shapes.rectangle;
import shapes.ellipse;
import mouse.mouse;

public class main extends JPanel implements MouseListener{

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

      public main(){
            addMouseListener(this);  
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

      Timer timer = new Timer();
      timer.schedule(new mouse(main.rects), 0, 50);
  }

  public void test(){
        System.out.println(8);
  }

  public void mouseClicked(MouseEvent e) { 
      
  }  

  public void mouseEntered(MouseEvent e) {}  
  public void mouseExited(MouseEvent e) {}  
  public void mousePressed(MouseEvent e) {
        // looping through each rectangle 
      for(int i = 0; i < rects.size(); i++){
            int x = e.getX();
            int y = e.getY();

            // looping through the width of the current rectangle
            for(int j = 0; j < rects.get(i).width; j++){
                  // checking if the current mouse.x is within the range of the rectangle width
                  if(x == rects.get(i).x + j){
                        // looping through the height of the rectangle
                        for(int k = 0; k < rects.get(i).height; k++){
                              // checking if the current mouse.y is within the range of the rectangle height
                              if(y == rects.get(i).y + k){
                                    rects.get(i).selected = true;
                              }
                        }
                  }
            }
      }
  }  
  public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < rects.size(); i++){
            rects.get(i).selected = false;
        }
  } 
}