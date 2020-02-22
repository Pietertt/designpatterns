import java.util.ArrayList;

import java.util.*;
import java.awt.*;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import shapes.rectangle;
import shapes.ellipse;
import mouse.mouse;

public class main extends JPanel implements MouseListener{

      //-----------------------------------------------------------------------------
      //                                  colors
      //-----------------------------------------------------------------------------
      private static int[] unselected = { 255, 0, 0 };
      private static int[] selected = { 255, 135, 135 };

      private static int offsetX = 200;
      private static int offsetY = 200;
      private static int width = 500;
      private static int height = 500;

      private static ArrayList<rectangle> rects = new ArrayList<rectangle>();
      private static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      private static JFrame frame;

      // paint method which is responsible for painting the window
      public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // fills and colors specific areas based on values in the 'rects' array
            for(int i = 0; i < rects.size(); i++){
                  g2d.setColor(new Color(rects.get(i).color[0], rects.get(i).color[1], rects.get(i).color[2]));
                  g2d.fillRect(rects.get(i).x, rects.get(i).y, rects.get(i).width, rects.get(i).height);
            }

            // fills and colors specific areas based on values in the 'ellipses' array
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

      // populates the field with the initial 5 rectangles
      for(int i = 0; i < 5; i++){
            main.rects.add(new rectangle(50 + i * 75, 50, 50, 50, i, main.unselected));
      }

      // populates the field with the initial 5 ellipses 
      for(int i = 0; i < 5; i++){
            main.ellipses.add(new ellipse(50 + i * 75, 150, 50, 50, main.unselected));
      }
      
      // some window settings
      main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main.frame.setSize(main.width, main.height);
      main.frame.setLocation(main.offsetX, main.offsetY);
      main.frame.setVisible(true);

      // the timer which fires 1000 times a second
      Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent event){
                  update();
            }
      });
      timer.setInitialDelay(0);
      timer.start();
  }

      public static void update(){
            //-----------------------------------------------------------------------------
            //                Responsible for dragging rectangles around
            //-----------------------------------------------------------------------------
            for(int i = 0; i < main.rects.size(); i++){
                  if(main.rects.get(i).selected){
                        Point a = MouseInfo.getPointerInfo().getLocation();

                        // the absolute X and Y values of the cursor
                        int xAbsolute = (int)a.getX();
                        int yAbsolute = (int)a.getY();

                        // determines the current mouse position regarding the rectangle X and Y values
                        int xRelative = (int)a.getX() - offsetX - rects.get(i).width / 2;
                        int yRelative = (int)a.getY() - offsetY - rects.get(i).height;

                        int xRect = xAbsolute - rects.get(i).width / 2;
                        int yRect = yAbsolute - rects.get(i).height;

                        // updates the current selected rectangle to the current mouse position
                        if(xRect > offsetX){ // the X position of the rectangle must be bigger than the window X offset
                              if(xRect < (offsetX + width - main.rects.get(i).width)){ // the X position of the rectangle must be bigger than the X offset of the screen + the height of the screen + the width of the rectangle / 2
                                    if(yRect > offsetY){ // the Y position of the rectangle must be bigger than the window Y offset
                                          if(yRect < (offsetY + height - main.rects.get(i).height)){ // the Y position of the rectangle must be bigger than the offset of the window + the height of the window - the height of the rectangle / 2
                                                main.rects.get(i).x = xRelative;
                                                main.rects.get(i).y = yRelative;   
                                          }
                                    }
                              }
                        }
                  } 
            }
            
            main.frame.repaint();
      }

  public void mouseClicked(MouseEvent e) { 
      
  }  

  public void mouseEntered(MouseEvent e) {}  
  public void mouseExited(MouseEvent e) {}  
  public void mousePressed(MouseEvent e) {
      //-----------------------------------------------------------------------------
      //                            Selection handler
      //-----------------------------------------------------------------------------

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
        // deselects all rectangles when the mouse is released
        for(int i = 0; i < rects.size(); i++){
            rects.get(i).selected = false;
        }
  } 
}