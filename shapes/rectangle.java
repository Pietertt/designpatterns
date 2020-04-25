package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

public class rectangle extends Shape  {
      private int locations[] = {
            SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST,
            SwingConstants.EAST, SwingConstants.NORTH_WEST,
            SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
            SwingConstants.SOUTH_EAST
      };
    
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

      private Handle getHandle(int location){
            switch(location){
                  case SwingConstants.NORTH:
                        System.out.println("Fuu");
                        return new Handle(100, 100, 8, 8);

                  // case SwingConstants.SOUTH:
                  //       return new Rectangle(x + w / 2 - dist / 2, y + h - dist, dist, dist);

                  // case SwingConstants.WEST:
                  //       return new Rectangle(x, y + h / 2 - dist / 2, dist, dist);

                  // case SwingConstants.EAST:
                  //       return new Rectangle(x + w - dist, y + h / 2 - dist / 2, dist, dist);

                  // case SwingConstants.NORTH_WEST:
                  //       return new Rectangle(x, y, dist, dist);

                  // case SwingConstants.NORTH_EAST:
                  //       return new Rectangle(x + w - dist, y, dist, dist);

                  // case SwingConstants.SOUTH_WEST:
                  //       return new Rectangle(x, y + h - dist, dist, dist);

                  // case SwingConstants.SOUTH_EAST:
                  //       return new Rectangle(x + w - dist, y + h - dist, dist, dist);
            }
            
            return null;
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
                        Handle handle = getHandle(locations[0]);

                              g.setColor(Color.BLACK);
                              g.fillRect(handle.x, handle.y, handle.width, handle.height);
                        
                        super.paintComponent(g);
                        g.setColor(Color.RED);
                        g.fillRect(x, y, width, height);
                        g.drawRect(x, y, width, height);
                  }
            }
      }
}