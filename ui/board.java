package ui;

import java.util.ArrayList;

import java.awt.*;


import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;
import java.util.List;
import java.util.Stack;

import commands.Order;
import shapes.Rectangle;
import commands.placeRectangle;
import shapes.ellipse;


public class board extends JPanel implements MouseListener {

      private static JFrame frame;
      public static ui ui;

      private List<Order> orderList = new ArrayList<Order>();

      private Stack<Order> undoStack;
      private Stack<Order> redoStack;
      private List<Rectangle> deletedRects = new ArrayList<>();

      private List<String> history;

      // //-----------------------------------------------------------------------------
      // //                                  colors
      // //-----------------------------------------------------------------------------

      public static int[] unselected = { 255, 0, 0 };
      public static int[] selected = { 255, 135, 135 };

      public static int[] GRAY = { 213, 213, 213 };
      public static int[] BLUE = { 76, 153, 229 };

      // //-----------------------------------------------------------------------------
      // //                                  Modes
      // //-----------------------------------------------------------------------------
      
      public int mode = 0;

      public boolean dragging = false;
      public boolean added = false;

      public static int offsetX = 200;
      public static int offsetY = 200;
      public static int width = 500;
      public static int height = 500;

      //public ArrayList<rectangle> rects = new ArrayList<rectangle>();
      public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
      public static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      public board(JFrame frame, ArrayList<Rectangle> rectangles, ArrayList<ellipse> ell, ui ui){
            undoStack = new Stack<>();
            redoStack = new Stack<>();
            history = new ArrayList<>();
            for(int i = 0; i < rectangles.size(); i++){
                  this.rects.add(rectangles.get(i));
            }
            this.frame = frame;
            this.ui = ui;
            addMouseListener(this);

            // populates the rectangle array with the initial 5 rectangles

      }


      public void initiate() {

      }

      // paint method which is responsible for painting the window
      @Override public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;



            for(int i = 0; i < this.rects.size(); i++){
                  if(this.rects.get(i).pressed){
                        this.rects.get(i).color = this.selected;
                  } else {
                        this.rects.get(i).color = this.unselected;
                  }
            }

            //IPlaceShape rect = new Rectangle();

            //rect.execute();

            undoStack.clear();
            //redoStack.clear();

             //fills and colors specific areas based on values in the 'rects' array
            for(int i = 0; i < this.rects.size(); i++){

                  //g2d.setColor(new Color(this.rects.get(i).color[0], this.rects.get(i).color[1], this.rects.get(i).color[2]));
                  //g2d.fillRect(this.rects.get(i).x, this.rects.get(i).y, this.rects.get(i).width, this.rects.get(i).height);
                  //Rectangle rectangle = new Rectangle(this.rects.get(i).x, this.rects.get(i).y,
                          //this.rects.get(i).width, this.rects.get(i).height, 6, this.GRAY);

                  placeRectangle placeRect = new placeRectangle(rects.get(i), frame, g2d);
                  this.undoStack.push(placeRect);
                  placeRect.execute();
                  //frame.add();
                  //frame.getContentPane().add(rects.get(i));
                  //this.add(rects.get(i));
            }


            // fills and colors specific areas based on values in the 'ellipses' array
            // for(int i = 0; i < ellipses.size(); i++){
            //       g2d.setColor(new Color(ellipses.get(i).color[0], ellipses.get(i).color[1], ellipses.get(i).color[2]));
            //       g2d.fill(new Ellipse2D.Double(ellipses.get(i).x, ellipses.get(i).y, ellipses.get(i).width, ellipses.get(i).height));
            // }
      }

      public void undoDrawRectangle() {
            System.out.println("Stack: " + undoStack);
            Order opt = undoStack.pop();
            redoStack.add(opt);
            int indexOfLastRect = rects.size() - 1;
            deletedRects.add(rects.get(indexOfLastRect));
            rects.remove(indexOfLastRect);
            opt.undo();
      }

      public void redoDrawRectangle() {
            System.out.println("Stack: " + redoStack);
            Order opt = redoStack.pop();
            rects.addAll(deletedRects);
            opt.redo();
      }


      public ArrayList<Rectangle> update() {

            //rects.clear();
      for (int i = 0; i < this.rects.size(); i++) {
            //rects.clear();
            Rectangle rect = this.rects.get(i).update(ui);
            rects.remove(rects.get(i));
            rects.add(rect);
      }

      //this.mode = ui.getMode();

      //-----------------------------------------------------------------------------
      //                Responsible for dragging rectangles around
      //-----------------------------------------------------------------------------

            // decides what to execute based on the current mode
            // 0 ------------> default, allows dragging of rectangles
            // 1 ------------> a rectangle is being created
//            switch(this.mode){
//                  case 0:
//                        for(int i = 0; i < this.rects.size(); i++){
//                              if(this.rects.get(i).selected){
//                                    Point a = MouseInfo.getPointerInfo().getLocation();
//
//                                    // the absolute X and Y values of the cursor
//                                    int xAbsolute = (int)a.getX();
//                                    int yAbsolute = (int)a.getY();
//
//                                    // determines the current mouse position regarding the rectangle X and Y values
//                                    int xRelative = (int)a.getX() - offsetX - this.rects.get(i).width / 2;
//                                    int yRelative = (int)a.getY() - offsetY - this.rects.get(i).height;
//
//                                    int xRect = xAbsolute - this.rects.get(i).width / 2;
//                                    int yRect = yAbsolute - this.rects.get(i).height;
//
//                                    // updates the current selected rectangle to the current mouse position
//                                    if(xRect > offsetX){ // the X position of the rectangle must be bigger than the window X offset
//                                          if(xRect < (offsetX + width - this.rects.get(i).width)){ // the X position of the rectangle must be bigger than the X offset of the screen + the height of the screen + the width of the rectangle / 2
//                                                if(yRect > offsetY){ // the Y position of the rectangle must be bigger than the window Y offset
//                                                      if(yRect < (offsetY + height - this.rects.get(i).height)){ // the Y position of the rectangle must be bigger than the offset of the window + the height of the window - the height of the rectangle / 2
//                                                            this.rects.get(i).x = xRelative;
//                                                            this.rects.get(i).y = yRelative;
//                                                      } else {
//                                                            this.rects.get(i).y = height - this.rects.get(i).height;
//                                                      }
//                                                } else {
//                                                      this.rects.get(i).y = 0;
//                                                }
//                                          } else {
//                                                this.rects.get(i).x = width - this.rects.get(i).width;
//                                          }
//                                    } else {
//                                          this.rects.get(i).x = 0;
//                                    }
//                              }
//                        }
//                        break;
//                  case 1:
//                        if(this.dragging == true){
//                              Rectangle rect = this.rects.get(this.rects.size() - 1);
//                              Point a = MouseInfo.getPointerInfo().getLocation();
//                              int x = (int)a.getX();
//                              int y = (int)a.getY();
//
//                              if((x - offsetX) > rect.x){
//                                    rect.width = (x - offsetX) - rect.x;
//                                    rect.height = (y - offsetY) - rect.y;
//                              }
//                        }
//                        break;
//                  default:
//                        break;
//            }
            
            // updates the frame which is given as a parameter
            frame.repaint();
            // returns the rectangle arraylist for further use to the main program
            return this.rects;
      }

      public void mouseClicked(MouseEvent e) { 

      }  
    
      public void mouseEntered(MouseEvent e) {}  
      public void mouseExited(MouseEvent e) {}  
      public void mousePressed(MouseEvent e) {
            //System.out.println(e.getX());

            if(this.mode == 1) {
                  if (this.added == false) {
                        int[] rgb = {255, 0, 0};
                        Point a = MouseInfo.getPointerInfo().getLocation();
                        //this.added = true;
                        //this.dragging = true;
                        this.rects.add(new Rectangle((int) a.getX() - offsetX, (int) a.getY() - offsetY, 1, 1, 1, this.BLUE, true, true));
                  }
            }
      }  
      public void mouseReleased(MouseEvent e) {
            if(this.mode == 0) {
                  for(int i = 0; i < this.rects.size(); i++){
                        rects.get(i).selected = false;
                  }
            }
      }
}