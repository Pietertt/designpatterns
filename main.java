import java.util.ArrayList;

import java.util.*;

import javax.swing.Timer;

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

      private int[] unselected = { 255, 0, 0 };
      private int[] selected = { 255, 135, 135 };

      private static ArrayList<rectangle> rects = new ArrayList<rectangle>();
      private static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      private static JFrame frame;

      public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            for(int i = 0; i < rects.size(); i++){
                  g2d.setColor(new Color(rects.get(i).color[0], rects.get(i).color[1], rects.get(i).color[2]));
                  g2d.fillRect(rects.get(i).x, rects.get(i).y, rects.get(i).width, rects.get(i).height);
            }

            for(int i = 0; i < ellipses.size(); i++){
                  g2d.setColor(new Color(ellipses.get(i).color[0], ellipses.get(i).color[1], ellipses.get(i).color[2]));
                  g2d.fill(new Ellipse2D.Double(ellipses.get(i).x, ellipses.get(i).y, ellipses.get(i).width, ellipses.get(i).height));
            }
      }

      public main(){
            addMouseListener(this);  
      }

  public static void main(String[] args) {
      main main = new main();

      main.frame = new JFrame();

      main.frame.add(main);

      for(int i = 0; i < 5; i++){
            main.rects.add(new rectangle(50 + i * 75, 50, 50, 50, i, main.unselected));
      }

      for(int i = 0; i < 5; i++){
            main.ellipses.add(new ellipse(50 + i * 75, 150, 50, 50, main.unselected));
      }
      
      main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main.frame.setSize(500, 500);
      main.frame.setLocationRelativeTo(null);
      main.frame.setVisible(true);

      Timer timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent event){
                  for(int i = 0; i < main.rects.size(); i++){
                        if(main.rects.get(i).selected){
                              main.rects.get(i).color = main.selected;
                        } else {
                              main.rects.get(i).color = main.unselected;
                        }
                  }
                  update();
            }
      });
      timer.setInitialDelay(0);
      timer.start();
  }

      public static void update(){
            main.frame.repaint();
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