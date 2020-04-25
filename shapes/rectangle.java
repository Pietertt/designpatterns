package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

public class rectangle extends Shape  {
    
      public rectangle(int x, int y, int width, int height, int id){
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
            if(selected){
                  if(rectDraw) {
                        super.paintComponent(g);
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, width, height);
                        g.drawRect(x, y, width, height);
                  }
            } else {
                  if(rectDraw){
                        super.paintComponent(g);
                        g.setColor(Color.RED);
                        g.fillRect(x, y, width, height);
                        g.drawRect(x, y, width, height);
                  }
            }
      }
}