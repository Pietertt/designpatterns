package strategies;

import commands.*;
import shapes.*;
import UI.Board;
import UI.Invoker;

import java.awt.Color;
import java.awt.Graphics;

public final class PlaceTriangleStrategy extends Strategy {
      public static Strategy strategy;

      private PlaceTriangleStrategy(){

      }

      // Prevents the strategy from instantiating multiple times
      public static Strategy getInstance(){
            if(strategy == null){
                  strategy = new PlaceTriangleStrategy();
            }
            return strategy;
      }

      public String toString(){
            return "triangle";
      }

      public void execute(int x, int y, int width, int height, Graphics g, boolean selected){
            if(selected){
                  // Draws a triangle with a border when it is not selected
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillPolygon(new int[] {x, x + (width / 2), x + width}, new int[] {y + height, y, y + height}, 3);

                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.drawPolygon(new int[] {x, x + (width / 2), x + width}, new int[] {y + height, y, y + height}, 3);

                  g.setColor(Color.WHITE);
                  g.fillOval(x + width - 6, y + height - 6, 12, 12);

                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.fillOval(x + width - 4, y + height - 4, 8, 8);
            }
            else {
                  // Draws a triangle when it is not selected
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillPolygon(new int[] {x, x + (width / 2), x + width}, new int[] {y + height, y, y + height}, 3);
            }
      }
}