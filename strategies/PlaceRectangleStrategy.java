package strategies;

import commands.*;
import shapes.*;
import UI.Board;
import UI.Invoker;

import java.awt.Color;
import java.awt.Graphics;

public final class PlaceRectangleStrategy extends Strategy {
      public static Strategy strategy;

      private PlaceRectangleStrategy(){

      }

      // Prevents the strategy from instantiating multiple times
      public static Strategy getInstance(){
            if(strategy == null){
                  strategy = new PlaceRectangleStrategy();
            }
            return strategy;
      }

      public String toString(){
            return "rectangle";
      }

      public void execute(int x, int y, int width, int height, Graphics g, boolean selected){
            if(selected){
                  // Draws a rectangle with a border when it is selected
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillRect(x, y, width, height);
                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.drawRect(x, y, width, height);

                  g.setColor(Color.WHITE);
                  g.fillOval(x + width - 6, y + height - 6, 12, 12);

                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.fillOval(x + width - 4, y + height - 4, 8, 8);
            }
            else {
                  // Draws a rectangle when it is not selected
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillRect(x, y, width, height);
            }
      }
}