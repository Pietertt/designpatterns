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

      @Override
      public void paintComponent(Graphics g) {
            if(selected){
                 
                        super.paintComponent(g);
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, width, height);
                        g.drawRect(x, y, width, height);
                  
            } else {
                        System.out.println();
                        
                        super.paintComponent(g);
                        g.setColor(Color.RED);
                        g.fillRect(x, y, width, height);
                        g.drawRect(x, y, width, height);

                        for(int i = 0; i < this.locations.length; i++){
                              Handle handle = getHandle(this.locations[i]);

                              g.setColor(Color.WHITE);
                              g.fillRect(handle.x, handle.y, handle.width, handle.height);

                              g.setColor(Color.BLACK);
                              g.drawRect(handle.x, handle.y, handle.width, handle.height);
                        }
                  
            }
      }


      private Handle getHandle(int location){
            switch(location){
                  case SwingConstants.NORTH:
                        return new Handle(this.x + this.width / 2 - 4, this.y - 4, 8, 8);

                  case SwingConstants.SOUTH:
                        return new Handle(this.x + this.width / 2 - 4, this.y + this.height - 4, 8, 8);

                  case SwingConstants.WEST:
                        return new Handle(this.x - 4, this.y + this.height / 2 - 4, 8, 8);

                  case SwingConstants.EAST:
                        return new Handle(this.x + this.width - 4, this.y + this.height / 2 - 4, 8, 8);

                  case SwingConstants.NORTH_WEST:
                        return new Handle(this.x - 4, this.y - 4, 8, 8);

                  case SwingConstants.NORTH_EAST:
                        return new Handle(this.x + this.width - 4, this.y - 4, 8, 8);

                  case SwingConstants.SOUTH_WEST:
                        return new Handle(this.x - 4, this.y + this.height - 4, 8, 8);

                  case SwingConstants.SOUTH_EAST:
                        return new Handle(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
            }
            
            return null;
      }
}