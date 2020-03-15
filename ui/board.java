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

import commands.*;
import shapes.Rectangle;
import shapes.ellipse;


public class board extends JPanel implements MouseListener {

      private static JFrame frame;
      private static ui ui;

      private static final List<Order> orderList = new ArrayList<Order>();

      private static final  Stack<Order> undoStack = new Stack<>();
      private static final  Stack<Order> redoStack = new Stack<>();
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

      dragRectangle dragRectangle;

      //private Graphics2D g2d;

      //public ArrayList<rectangle> rects = new ArrayList<rectangle>();
      public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
      public static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      public board(JFrame frame, ArrayList<ellipse> ell, ui ui){
            history = new ArrayList<>();
//            for(int i = 0; i < rectangles.size(); i++){
//                  this.rects.add(rectangles.get(i));
//            }
            this.frame = frame;
            this.ui = ui;
            addMouseListener(this);

            // populates the rectangle array with the initial 5 rectangles

      }


      public void initiate() {
            for(int i = 0; i < 5; i++){
                  Rectangle rectangle = new Rectangle(50 + i * 75, 50, 50, 50, i, board.unselected);
                  rects.add(rectangle);
                  placeRectangle placeRectangle = new placeRectangle(rects.get(i), this);
                  //placeRectangle.execute();
                  this.undoStack.add(placeRectangle);
            }
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

            //undoStack.clear();
            //redoStack.clear();


            for(int i = 0; i < this.undoStack.size(); i++) {
                  if(undoStack.get(i).getName().equals("place")) {
                        undoStack.get(i).addGraphics(g2d);
                        undoStack.get(i).execute();
                  }
            }

             //fills and colors specific areas based on values in the 'rects' array
//            for(int i = 0; i < this.rects.size(); i++){
//                  placeRectangle placeRect = new placeRectangle(rects.get(i), g2d, this);
//                  //this.undoStack.push(placeRect);
//                  placeRect.execute();
//                  //this.addMouseListener(rects.get(i));
//            }


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
//            int indexOfLastRect = rects.size() - 1;
//            deletedRects.add(rects.get(indexOfLastRect));
//            rects.remove(indexOfLastRect);
            opt.undo();
      }

      public void redoDrawRectangle() {
            System.out.println("Stack: " + redoStack);
            Order opt = redoStack.pop();
            undoStack.add(opt);
            //rects.addAll(deletedRects);
            opt.redo();
      }


      public ArrayList<Rectangle> update() {


      for (int i = 0; i < this.rects.size(); i++) {
            this.dragRectangle = new dragRectangle(this.rects.get(i), ui.getMode());
            //Rectangle rect = this.rects.get(i).update(ui);


            //this.undoStack.push(dragRectangle);
            this.dragRectangle.execute();

            //this.undoStack.add(dragRectangle); // VERPLAATSEN.. wordt telkens weer toegevoegd.
/*            rects.remove(rects.get(i));
            rects.add(rects.get(i));*/
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
            this.repaint();
            //frame.repaint();
            // returns the rectangle arraylist for further use to the main program
            return this.rects;
      }

      public void mouseClicked(MouseEvent e) {
            switch(this.mode){
                  case 0:
                        for(Rectangle rect : rects) {
                              pressRectangle pressRectangle = new pressRectangle(rect, e);

                              pressRectangle.execute();
                              if(rect.pressed) {
                                    this.undoStack.push(pressRectangle);
                              }
                        }
                        break;
                  case 1:

                        break;
                  default:
                        break;
            }
      }

      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
      public void mousePressed(MouseEvent e) {
            switch(this.mode){
                 case 0:
                       for(Rectangle rect : rects) {
                             selectRectangle selectRectangle = new selectRectangle(rect, e);
                             selectRectangle.execute();
                             if(rect.selected) {
                                   this.undoStack.push(selectRectangle);
                             }
                       }
                       break;
                  case 1:
                        System.out.println("Mouse Pressed");
                        if (this.added == false) {
                              int[] rgb = {255, 0, 0};
                              Point a = MouseInfo.getPointerInfo().getLocation();
                              this.rects.add(new Rectangle((int) a.getX() - offsetX, (int) a.getY() - offsetY, 1, 1, 6, rgb));
                              this.added = true;
                              this.dragging = true;
                        }
                        break;
                  default:
                        break;
            }
      }

      public void mouseReleased(MouseEvent e) {

            switch(this.mode) {
                  case 0:
                        for(int i = 0; i < this.rects.size(); i++){
                              rects.get(i).selected = false;
                        }
                        break;
                  case 1:
                        this.dragging = false;
                        this.added = false;
                        //this.mode = 0;
                        ui.setMode(0);
                        break;
                  default:
                        break;
            }
      }
}