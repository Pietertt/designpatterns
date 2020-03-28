package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class rectangle extends JComponent implements receiver, MouseMotionListener {
      public int id;

      // Moet de rectangle getekend worden
      private boolean rectDraw = false;

      // Is de rectangle geselecteerd
      private boolean selected = false;

      // Is dragging toggled on
      private boolean dragging = false;

      private int x;
      private int y;
      private int width;
      private int height;

      // Save width & height to redraw.
      private int savedWidth;
      private int savedHeight;

      public rectangle(int x, int y, int width, int height, int id){
            //super(x, y, width, height);
            this.x = x;
            this.y = y;
            this.width = width;
            this.savedWidth= width;
            this.height = height;
            this.savedHeight = height;
            this.id = id;
      }

      private void setColor(Graphics g) {
            if(selected)
                  g.setColor(Color.GRAY);
            else
                  g.setColor(Color.BLUE);
      }

      @Override
      public void paintComponent(Graphics g) {
            if(rectDraw) {
                  super.paintComponent(g);

                  //Color c = new Color(1.0F, 0.0F, 1.0F);
                  //g.setColor(c);
                  setColor(g);
                  g.fillRect(x, y, width, height);
                  g.drawRect(x, y, width, height);
            }
      }

      public void dragIsOn() {
            dragging = true;
      }

      public void dragIsOff() {
            dragging = false;
      }

      public void setDrawTrue() {
            this.width = savedWidth;
            this.height = savedHeight;
            rectDraw = true;
            repaint();
      }

      public void setDrawFalse() {
            // size has to be set to zero to ensure you don't select invisible rectangles
            this.width = 0;
            this.height = 0;
            rectDraw = false;
            repaint();
      }

      public void setSelectedTrue() {
            if(rectDraw)
                  selected = true;
            repaint();
      }

      public void setSelectedFalse() {
            if(rectDraw)
                  selected = false;
            repaint();
      }

      public boolean getSelected() {
            return selected;
      }

      public boolean getIfSelected(int x, int y) {
            for(int i = 0; i < this.width; i++){
                  if(x == this.x + i){
                        for(int j = 0; j < this.height; j++){
                              if(y == this.y + j){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      @Override
      public void mouseDragged(MouseEvent e) {
            if(selected /*&& dragging*/) {
                  this.x = e.getX();
                  this.y = e.getY();
                  repaint();
            }
      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }
}