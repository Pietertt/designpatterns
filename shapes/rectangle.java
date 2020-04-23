package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

public class rectangle extends shape  {
    

      public rectangle(int x, int y, int width, int height, int id){
            //super(x, y, width, height);
            this.x = x;
            this.savedX = x;
            this.y = y;
            this.savedY = y;
            this.width = width;
            this.savedWidth= width;
            this.height = height;
            this.savedHeight = height;
            this.id = id;
            this.undoStack = new Stack<>();
            this.redoStack = new Stack<>();
      }

      @Override
      public void paintComponent(Graphics g) {
            if(rectDraw) {
                  super.paintComponent(g);
                  setColor(g);
                  g.fillRect(x, y, width, height);
                  g.drawRect(x, y, width, height);
            }
      }

      

      @Override
      public void mouseDragged(MouseEvent e) {
            if(selected && dragging) {
                  this.x = e.getX();
                  this.y = e.getY();
                  repaint();
            }
      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }
}