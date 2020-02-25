package board;

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
import board.board;

public class board extends JPanel implements MouseListener {

      private static JFrame frame;

      // //-----------------------------------------------------------------------------
      // //                                  colors
      // //-----------------------------------------------------------------------------

      public static int[] unselected = { 255, 0, 0 };
      public static int[] selected = { 255, 135, 135 };

      // //-----------------------------------------------------------------------------
      // //                                  Modes
      // //-----------------------------------------------------------------------------  
      
      public int mode = 0;

      public boolean dragging = false;

      public static int offsetX = 200;
      public static int offsetY = 200;
      public static int width = 500;
      public static int height = 500;

      public ArrayList<rectangle> rects = new ArrayList<rectangle>();
      public static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      public board(JFrame frame, ArrayList<rectangle> rectangles, ArrayList<ellipse> ell){
            for(int i = 0; i < rectangles.size(); i++){
                  this.rects.add(rectangles.get(i));
            }
            this.frame = frame;
            addMouseListener(this);  
      }

      // paint method which is responsible for painting the window
      @Override public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // fills and colors specific areas based on values in the 'rects' array
            for(int i = 0; i < this.rects.size(); i++){
                  g2d.setColor(new Color(this.rects.get(i).color[0], this.rects.get(i).color[1], this.rects.get(i).color[2]));
                  g2d.fillRect(this.rects.get(i).x, this.rects.get(i).y, this.rects.get(i).width, this.rects.get(i).height);
            }

            // fills and colors specific areas based on values in the 'ellipses' array
            // for(int i = 0; i < ellipses.size(); i++){
            //       g2d.setColor(new Color(ellipses.get(i).color[0], ellipses.get(i).color[1], ellipses.get(i).color[2]));
            //       g2d.fill(new Ellipse2D.Double(ellipses.get(i).x, ellipses.get(i).y, ellipses.get(i).width, ellipses.get(i).height));
            // }
      }
      

      public ArrayList<rectangle> update(int mode){
            this.mode = mode;
      //-----------------------------------------------------------------------------
      //                Responsible for dragging rectangles around
      //-----------------------------------------------------------------------------

            // decides what to execute based on the current mode
            // 0 ------------> default, allows dragging of rectangles
            // 1 ------------> a rectangle is being created
            switch(this.mode){ 
                  case 0: 
                        for(int i = 0; i < this.rects.size(); i++){
                              if(this.rects.get(i).selected){
                                    Point a = MouseInfo.getPointerInfo().getLocation();

                                    // the absolute X and Y values of the cursor
                                    int xAbsolute = (int)a.getX();
                                    int yAbsolute = (int)a.getY();

                                    // determines the current mouse position regarding the rectangle X and Y values
                                    int xRelative = (int)a.getX() - offsetX - this.rects.get(i).width / 2;
                                    int yRelative = (int)a.getY() - offsetY - this.rects.get(i).height;

                                    int xRect = xAbsolute - this.rects.get(i).width / 2;
                                    int yRect = yAbsolute - this.rects.get(i).height;

                                    // updates the current selected rectangle to the current mouse position
                                    if(xRect > offsetX){ // the X position of the rectangle must be bigger than the window X offset
                                          if(xRect < (offsetX + width - this.rects.get(i).width)){ // the X position of the rectangle must be bigger than the X offset of the screen + the height of the screen + the width of the rectangle / 2
                                                if(yRect > offsetY){ // the Y position of the rectangle must be bigger than the window Y offset
                                                      if(yRect < (offsetY + height - this.rects.get(i).height)){ // the Y position of the rectangle must be bigger than the offset of the window + the height of the window - the height of the rectangle / 2
                                                            this.rects.get(i).x = xRelative;
                                                            this.rects.get(i).y = yRelative;   
                                                      } else {
                                                            this.rects.get(i).y = height - this.rects.get(i).height;
                                                      }
                                                } else {
                                                      this.rects.get(i).y = 0;
                                                }
                                          } else {
                                                this.rects.get(i).x = width - this.rects.get(i).width;
                                          }
                                    } else {
                                          this.rects.get(i).x = 0;
                                    }
                              } 
                        }
                        break;
                  case 1:
                        
                        break;
                  default:
                        break;
            }
            
            // updates the frame which is given as a parameter
            frame.repaint();
            //System.out.println(this.dragging);
            // returns the rectangle arraylist for further use to the main program
            return this.rects;
      }

      public void addRectangle(rectangle rect){
            this.rects.add(rect);
      }

      public void mouseClicked(MouseEvent e) { 
            switch(this.mode){
                  case 0:
                        break;
                  case 1:
                        if(dragging == false){
                              int[] rgb = { 255, 0, 0 };
                              Point a = MouseInfo.getPointerInfo().getLocation();
                              this.rects.add(new rectangle((int)a.getX() - offsetX, (int)a.getY() - offsetY, 50, 50, 6, rgb));
                              this.dragging = true;
                        }
                        break;
                  default:
                        break;
            }
      }  
    
      public void mouseEntered(MouseEvent e) {}  
      public void mouseExited(MouseEvent e) {}  
      public void mousePressed(MouseEvent e) {
            //-----------------------------------------------------------------------------
            //                            Selection handler
            //-----------------------------------------------------------------------------
    
            switch(this.mode){
                  case 0:
                        // looping through each rectangle 
                        for(int i = 0; i < this.rects.size(); i++){
                              int x = e.getX();
                              int y = e.getY();
            
                              // looping through the width of the current rectangle
                              for(int j = 0; j < this.rects.get(i).width; j++){
                                    // checking if the current mouse.x is within the range of the rectangle width
                                    if(x == this.rects.get(i).x + j){
                                          // looping through the height of the rectangle
                                          for(int k = 0; k < this.rects.get(i).height; k++){
                                                // checking if the current mouse.y is within the range of the rectangle height
                                                if(y == this.rects.get(i).y + k){
                                                      this.rects.get(i).selected = true;
                                                }
                                          }
                                    }
                              }
                        }
                        break;
                  case 1:
                        this.dragging = true;
                        System.out.println(this.dragging);
                        break;
                  default:
                        break;
            }
      }  
      public void mouseReleased(MouseEvent e) {
            switch(this.mode){
                  case 0:
                         // deselects all rectangles when the mouse is released
                        for(int i = 0; i < this.rects.size(); i++){
                              this.rects.get(i).selected = false;
                        }
                        break;
                  case 1:
                        this.dragging = false;
                        this.mode = 0;
                        System.out.println(this.dragging);
                        break;
                  default:
                        break;
            }
      } 

      public int getMode(){
            return this.mode;
      }
}