package strategies;

import commands.*;
import shapes.*;
import UI.Board;
import UI.Invoker;

import java.awt.Color;
import java.awt.Graphics;

public final class PlaceGroupStrategy extends Strategy {
      public static Strategy strategy;

      private PlaceGroupStrategy(){

      }

      // Prevents the strategy from instantiating multiple times
      public static Strategy getInstance(){
            if(strategy == null){
                  strategy = new PlaceGroupStrategy();
            }
            return strategy;
      }

      public String toString(){
            return "group";
      }

      public void execute(int x, int y, int width, int height, Graphics g, boolean selected){
            if(selected){
                  // Draws the group when it is selected
                  g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                  g.drawRect(x, y, width, height);

                  g.setColor(Color.WHITE);
                  g.fillOval(x + width - 6, y + height - 6, 12, 12);

                  g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                  g.fillOval(x + width - 4, y + height - 4, 8, 8);
            }
      }
}