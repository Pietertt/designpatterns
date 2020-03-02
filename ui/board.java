package ui;

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
import javax.swing.KeyStroke;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import shapes.rectangle;
import shapes.ellipse;

import javax.swing.*;

import javax.swing.border.Border;

import ui.ui;

public class board extends JPanel implements MouseListener {

      private static JFrame frame;
      private static ui ui;

      // //-----------------------------------------------------------------------------
      // //                                  colors
      // //-----------------------------------------------------------------------------

      public static int[] GRAY = { 213, 213, 213 };
      public static int[] BLUE = { 76, 153, 229 };

      // //-----------------------------------------------------------------------------
      // //                                  Modes
      // //-----------------------------------------------------------------------------  
      
      public int mode = 0;

      public int currentlySelected;

      public boolean dragging = false;
      public boolean added = false;

      public static int offsetX = 200;
      public static int offsetY = 200;
      public static int width = 500;
      public static int height = 500;

      public int lastX = 0;
      public int lastY = 0;

      public ArrayList<rectangle> rects = new ArrayList<rectangle>();
      public static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      public board(JFrame frame, ArrayList<rectangle> rectangles, ArrayList<ellipse> ell, ui ui){
            for(int i = 0; i < rectangles.size(); i++){
                  this.rects.add(rectangles.get(i));
            }
            this.frame = frame;
            super.setFocusable(true);
            this.ui = ui;
            addMouseListener(this);  

            // fires an event with the 'DELETE' identifier when the backspace is pressed
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "DELETE");
            getActionMap().put("DELETE", delete);
      }

      // paint method which is responsible for painting the window
      @Override public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint( RenderingHints. KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            // fills and colors specific areas based on values in the 'rects' array
            for(int i = 0; i < this.rects.size(); i++){
                  rectangle rect = this.rects.get(i);

                  if(rect.selected){
                        //draw a rectangle which is slightly bigger to act as a border
                        g2d.setColor(new Color(this.BLUE[0], this.BLUE[1], this.BLUE[2]));
                        g2d.fillRect(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);

                        for(int j = 0; j < rect.handles.size(); j++){
                              g2d.setColor(new Color(255, 255, 255));
                              g2d.fillOval(rect.handles.get(j).x - 2, rect.handles.get(j).y - 2, rect.handles.get(j).width + 4, rect.handles.get(j).height + 4);
                              g2d.setColor(new Color(this.BLUE[0], this.BLUE[1], this.BLUE[2]));
                              g2d.fillOval(rect.handles.get(j).x, rect.handles.get(j).y, rect.handles.get(j).width, rect.handles.get(j).height);
                        }
                  }

                  // draw the rectangle 
                  g2d.setColor(new Color(rect.color[0], rect.color[1], rect.color[2]));
                  g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
      }
      

      public ArrayList<rectangle> update(){

      this.mode = ui.getMode();
            // decides what to execute based on the current mode
            // 0 ------------> default, allows dragging of rectangles
            // 1 ------------> a rectangle is being created

            switch(this.mode){ 
                  case 0: 
                        for(int i = 0; i < this.rects.size(); i++){
                              if(this.rects.get(i).moving){
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
                                                            this.rects.get(i).select();
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
                        if(this.dragging == true){
                              // updates the values of a rectangle to the mouse coordinates when dragging is allowed
                              // the newest rectangle is selected
                              rectangle rect = this.rects.get(this.rects.size() - 1);
                              Point a = MouseInfo.getPointerInfo().getLocation();

                              // resizes the rectangle when the mouse position minus the offset is larger than the INITIAL x value of the rectangle
                              if(((int)a.getX() - offsetX) > rect.x){
                                    rect.resize(offsetX, offsetY, (int)a.getX(), (int)a.getY());
                              }
                        }
                        break;
                  default:
                        break;
            }
            
            // updates the frame
            frame.repaint();
            // returns the rectangle arraylist for further use to the main program
            return this.rects;
      }

      public void mouseClicked(MouseEvent e) {       
            switch(this.mode){
                  case 0:
                        // the absolute X and Y values of the cursor
                        int x = e.getX();
                        int y = e.getY();

                        // determines where the user clicked and sets the 'selected' property of a rectangle to true when the user clicks in a rectangle
                        for(int i = 0; i < this.rects.size(); i++){
                              rectangle rect = this.rects.get(i);

                              for(int j = 0; j < rect.handles.size(); j++){
                                    handle handle = rect.handles.get(j);
                                    for(int k = 0; k < rect.width; k++){
                                          if(x == handle.x + k){
                                                for(int l = 0; l < rect.height; l++){
                                                      if(y == handle.y + l){
                                                            rect.handles.get(j).selected = true;
                                                      }
                                                }
                                          }
                                    }
                              }

                              //rect.selected = false;
                              for(int j = 0; j < rect.width; j++){
                                    if(x == rect.x + j){
                                          for(int k = 0; k < rect.height; k++){
                                                if(y == rect.y + k){
                                                      rect.selected = true;
                                                      rect.select(); // created handles to enable resizing
                                                      this.currentlySelected = i;
                                                } 
                                          }
                                    }
                              }

                              
                        }

                  case 1:
                        
                        break;
                  default:
                        break;
            }
      }  
    
      public void mouseEntered(MouseEvent e) {
            
      }  
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
                                                      this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                                      if(this.rects.get(i).selected){
                                                            this.rects.get(i).moving = true;
                                                      }
                                                }
                                          }
                                    }
                              }
                        }

                  
                        break;
                  case 1:
                        if(this.added == false){
                              // creates a tiny rectangle to start dragging
                              Point a = MouseInfo.getPointerInfo().getLocation();
                              this.rects.add(new rectangle((int)a.getX() - offsetX, (int)a.getY() - offsetY, 1, 1, 6, this.GRAY));
                              this.added = true;
                              this.dragging = true;
                        } 
                        break;
                  default:
                        break;
            }
      }  

      public void mouseReleased(MouseEvent e) {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            switch(this.mode){
                  case 0:
                         // deselects all rectangles when the mouse is released
                        for(int i = 0; i < this.rects.size(); i++){
                              this.rects.get(i).moving = false;
                        }
                        break;
                  case 1:
                        // resets the 'dragging' and 'added' variables to enable dragging later on 
                        this.dragging = false;
                        this.added = false;
                        for(int i = 0; i < this.rects.size(); i++){
                              this.rects.get(i).selected = false;
                        }
                        // set the mode to 0 to enable selecting and moving rectangles
                        this.ui.setMode(0);
                        break;
                  default:
                        break;
            }
      } 

      Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                  // determines which rectangle is selected and removing it from the arraylist
                  for(int i = 0; i < rects.size(); i++){
                        if(rects.get(i).selected){
                              rects.remove(i);
                        }
                }
            }
        };
}