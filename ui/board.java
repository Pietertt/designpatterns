package ui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import shapes.rectangle;
import shapes.ellipse;
import shapes.handle;

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

      public boolean dragging = false;
      public boolean added = false;

      public static int offsetX = 200;
      public static int offsetY = 200;
      public static int width = 600;
      public static int height = 600;

      public ArrayList<rectangle> rects = new ArrayList<rectangle>();
      public ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      public board(JFrame frame, ArrayList<rectangle> rectangles, ArrayList<ellipse> ell, ui ui) {

            for (int i = 0; i < rectangles.size(); i++) {
                  this.rects.add(rectangles.get(i));
            }

            for (int i = 0; i < ell.size(); i++) {
                  this.ellipses.add(ell.get(i));
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
      @Override
      public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            for (int i = 0; i < this.ellipses.size(); i++) {
                  ellipse e = this.ellipses.get(i);

                  if (e.selected) {
                        g2d.setColor(new Color(this.BLUE[0], this.BLUE[1], this.BLUE[2]));
                        g2d.fillOval(e.x - 2, e.y - 2, e.width + 4, e.height + 4);

                        for (int j = 0; j < e.handles.size(); j++) {
                              g2d.setColor(new Color(255, 255, 255));
                              g2d.fillOval(e.handles.get(j).x - 6, e.handles.get(j).y - 6, e.handles.get(j).width + 4,
                                          e.handles.get(j).height + 4);
                              g2d.setColor(new Color(this.BLUE[0], this.BLUE[1], this.BLUE[2]));
                              g2d.fillOval(e.handles.get(j).x - 4, e.handles.get(j).y - 4, e.handles.get(j).width,
                                          e.handles.get(j).height);
                        }
                  }

                  g2d.setColor(new Color(e.color[0], e.color[1], e.color[2]));
                  g2d.fillOval(e.x, e.y, e.width, e.height);

            }

            // fills and colors specific areas based on values in the 'rects' array
            for (int i = 0; i < this.rects.size(); i++) {
                  rectangle rect = this.rects.get(i);

                  if (rect.selected) {
                        // draw a rectangle which is slightly bigger to act as a border
                        g2d.setColor(new Color(this.BLUE[0], this.BLUE[1], this.BLUE[2]));
                        g2d.fillRect(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);

                        for (int j = 0; j < rect.handles.size(); j++) {
                              g2d.setColor(new Color(255, 255, 255));
                              g2d.fillOval(rect.handles.get(j).x - 2, rect.handles.get(j).y - 2,
                                          rect.handles.get(j).width + 4, rect.handles.get(j).height + 4);
                              g2d.setColor(new Color(this.BLUE[0], this.BLUE[1], this.BLUE[2]));
                              g2d.fillOval(rect.handles.get(j).x, rect.handles.get(j).y, rect.handles.get(j).width,
                                          rect.handles.get(j).height);
                        }
                  }

                  // draw the rectangle
                  g2d.setColor(new Color(rect.color[0], rect.color[1], rect.color[2]));
                  g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
      }

      public ArrayList<rectangle> update() {
            this.offsetX = (int) frame.getLocation().getX();
            this.offsetY = (int) frame.getLocation().getY();

            this.width = frame.getWidth();
            this.height = frame.getHeight();

            this.mode = ui.getMode();
            // decides what to execute based on the current mode
            // 0 ------------> default, allows dragging of rectangles
            // 1 ------------> a rectangle is being created
            // 2 ------------> the rectangle is dragged

            switch (this.mode) {
                  case 0:
                        for (int i = 0; i < this.ellipses.size(); i++) {
                              ellipse e = this.ellipses.get(i);
                              if (e.moving) {
                                    Point a = MouseInfo.getPointerInfo().getLocation();

                                    // the absolute X and Y values of the cursor
                                    int xAbsolute = (int) a.getX();
                                    int yAbsolute = (int) a.getY();

                                    int xRelative = (int) a.getX() - this.offsetX - e.width;
                                    int yRelative = (int) a.getY() - this.offsetY - e.height;

                                    int xEll = xAbsolute - e.width;
                                    int yEll = yAbsolute - e.height;

                                    if (xEll > this.offsetX) { // the X position of the rectangle must be bigger than
                                                               // the window X offset
                                          if (xEll < (this.offsetX + width - e.width)) { // the X position of the
                                                                                         // rectangle must be bigger
                                                                                         // than the X offset of the
                                                                                         // screen + the height of the
                                                                                         // screen + the width of the
                                                                                         // rectangle / 2
                                                if (yEll > this.offsetY) { // the Y position of the rectangle must be
                                                                           // bigger than the window Y offset
                                                      if (yEll < (this.offsetY + height - e.height)) { // the Y position
                                                                                                       // of the
                                                                                                       // rectangle must
                                                                                                       // be bigger than
                                                                                                       // the offset of
                                                                                                       // the window +
                                                                                                       // the height of
                                                                                                       // the window -
                                                                                                       // the height of
                                                                                                       // the rectangle
                                                                                                       // / 2
                                                            e.x = xRelative;
                                                            e.y = yRelative;
                                                            e.select();
                                                      } else {
                                                            e.y = height - e.height;
                                                      }
                                                } else {
                                                      e.y = 0;
                                                }
                                          } else {
                                                e.x = width - e.width;
                                          }
                                    } else {
                                          e.x = 0;
                                    }
                              }
                        }

                        for (int i = 0; i < this.rects.size(); i++) {
                              rectangle rect = this.rects.get(i);
                              if (this.rects.get(i).moving) {
                                    Point a = MouseInfo.getPointerInfo().getLocation();

                                    // the absolute X and Y values of the cursor
                                    int xAbsolute = (int) a.getX();
                                    int yAbsolute = (int) a.getY();

                                    // determines the current mouse position regarding the rectangle X and Y values
                                    int xRelative = (int) a.getX() - this.offsetX - rect.width / 2;
                                    int yRelative = (int) a.getY() - this.offsetY - rect.height;

                                    int xRect = xAbsolute - rect.width;
                                    int yRect = yAbsolute - rect.height;

                                    // updates the current selected rectangle to the current mouse position
                                    if (xRect > this.offsetX) { // the X position of the rectangle must be bigger than
                                                                // the window X offset
                                          if (xRect < (this.offsetX + width - rect.width)) { // the X position of the
                                                                                             // rectangle must be bigger
                                                                                             // than the X offset of the
                                                                                             // screen + the height of
                                                                                             // the screen + the width
                                                                                             // of the rectangle / 2
                                                if (yRect > this.offsetY) { // the Y position of the rectangle must be
                                                                            // bigger than the window Y offset
                                                      if (yRect < (this.offsetY + height - rect.height)) { // the Y
                                                                                                           // position
                                                                                                           // of the
                                                                                                           // rectangle
                                                                                                           // must be
                                                                                                           // bigger
                                                                                                           // than the
                                                                                                           // offset of
                                                                                                           // the window
                                                                                                           // + the
                                                                                                           // height of
                                                                                                           // the window
                                                                                                           // - the
                                                                                                           // height of
                                                                                                           // the
                                                                                                           // rectangle
                                                                                                           // / 2
                                                            rect.x = xRelative;
                                                            rect.y = yRelative;
                                                            rect.select();
                                                      } else {
                                                            rect.y = height - rect.height;
                                                      }
                                                } else {
                                                      rect.y = 0;
                                                }
                                          } else {
                                                rect.x = width - rect.width;
                                          }
                                    } else {
                                          rect.x = 0;
                                    }
                              }
                        }
                        break;
                  case 1:
                        if (this.dragging == true) {
                              // updates the values of a rectangle to the mouse coordinates when dragging is
                              // allowed
                              // the newest rectangle is selected
                              rectangle rect = this.rects.get(this.rects.size() - 1);
                              Point a = MouseInfo.getPointerInfo().getLocation();

                              // resizes the rectangle when the mouse position minus the offset is larger than
                              // the INITIAL x value of the rectangle
                              if (((int) a.getX() - offsetX) > rect.x) {
                                    rect.resize(offsetX, offsetY, (int) a.getX(), (int) a.getY());
                              }
                        }
                        break;
                  case 2:
                        Point a = MouseInfo.getPointerInfo().getLocation();
                        int x = (int) a.getX();
                        int y = (int) a.getY();
                        
                        for (int i = 0; i < this.rects.size(); i++) {
                              rectangle rect = this.rects.get(i);
                              for (int j = 0; j < rect.handles.size(); j++) {
                                    handle handle = rect.handles.get(j);
                                    if (handle.selected) {
                                          rect.width = (x - offsetX) - rect.x;
                                          rect.height = (y - offsetY) - rect.y;
                                          handle.update(x - offsetX, y - offsetY);
                                    }
                              }
                        }

                        for(int i = 0; i < this.ellipses.size(); i++){
                              ellipse ell = this.ellipses.get(i);
                              for(int j = 0; j < ell.handles.size(); j++){
                                    handle h = ell.handles.get(j);
                                    if (h.selected) {
                                          ell.width = (x - offsetX) - ell.x;
                                          ell.height = (y - offsetY) - ell.y;
                                          h.update(x - offsetX, y - offsetY);
                                    }
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
            switch (this.mode) {
                  case 0:
                        // the absolute X and Y values of the cursor
                        int x = e.getX();
                        int y = e.getY();

                        for (int i = 0; i < this.rects.size(); i++) {
                              rectangle rect = this.rects.get(i);
                              rect.selected = false;
                              if (rect.selected(x, y)) {
                                    rect.selected = true;
                                    rect.select(); // created handles to enable resizing
                              }
                        }

                        for (int i = 0; i < this.ellipses.size(); i++) {
                              ellipse ell = this.ellipses.get(i);
                              ell.selected = false;
                              if (ell.selected(x, y)) {
                                    ell.selected = true;
                                    ell.select();
                              }
                        }
                  default:
                        break;
            }
      }

      public void mouseEntered(MouseEvent e) {

      }

      public void mouseExited(MouseEvent e) {
      }

      public void mousePressed(MouseEvent e) {

            // -----------------------------------------------------------------------------
            // Selection handler
            // -----------------------------------------------------------------------------

            switch (this.mode) {
                  case 0:
                        for(int i = 0; i < this.ellipses.size(); i++){
                              int x = e.getX();
                              int y = e.getY();

                              ellipse ell = this.ellipses.get(i);

                              for(int j = 0; j < ell.handles.size(); j++){
                                    handle handle = ell.handles.get(j);
                                    if(handle.selected(x, y)){
                                          handle.selected = true;
                                          this.mode = 2;
                                          ui.setMode(2);
                                    }
                              }

                              if(ell.selected(x, y)){
                                    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                    if(ell.selected){
                                          ell.moving = true;
                                    } 
                              }
                        }

                        // looping through each rectangle
                        for (int i = 0; i < this.rects.size(); i++) {
                              int x = e.getX();
                              int y = e.getY();

                              rectangle rect = this.rects.get(i);

                              for(int j = 0; j < rect.handles.size(); j++){
                                    handle handle = rect.handles.get(j);
                                    if(handle.selected(x, y)){
                                          handle.selected = true;
                                          this.mode = 2;
                                          ui.setMode(2);
                                    }
                              }
            
                              // looping through the width of the current rectangle
                              if(rect.selected(x, y)){
                                    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                    if(rect.selected){
                                          rect.moving = true;
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

                        for(int i = 0; i < this.ellipses.size(); i++){
                              this.ellipses.get(i).moving = false;
                        }

                        break;
                  case 1:
                        // resets the 'dragging' and 'added' variables to enable dragging later on 
                        this.dragging = false;
                        this.added = false;

                        for(int i = 0; i < this.rects.size(); i++){
                              this.rects.get(i).selected = false;
                        }

                        for(int i = 0; i < this.ellipses.size(); i++){
                              this.ellipses.get(i).selected = false;
                        }

                        // set the mode to 0 to enable selecting and moving rectangles
                        this.ui.setMode(0);
                        break;
                  case 2:
                        this.mode = 0;
                        ui.setMode(0);
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

                  for(int i = 0; i < ellipses.size(); i++){
                        if(ellipses.get(i).selected){
                              ellipses.remove(i);
                        }
                  }
            }
      };
}