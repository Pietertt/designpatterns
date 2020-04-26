package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

public class ellipse extends shape  {
    
      public ellipse(int x, int y, int width, int height, int id){
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
            this.shapes = new ArrayList<>();
      }

      @Override
      public void paintComponent(Graphics g) {
            if(rectDraw) {
                  super.paintComponent(g);
                  setColor(g);
                  g.fillOval(x, y, width, height);
                  g.drawRect(x, y, width, height);
            }
      }
}