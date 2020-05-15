package strategies;

import commands.*;
import shapes.*;
import UI.Board;
import UI.Invoker;

import java.awt.Color;
import java.awt.Graphics;

public final class PlaceEllipseStrategy extends Strategy {

      public static Strategy getInstance(){
            if(strategy == null){
                  strategy = new PlaceEllipseStrategy();
            }
            return strategy;
      }

      public void execute(int x, int y, int width, int height, Graphics g, boolean selected){
            if(selected){
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillOval(x, y, width, height);
                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.drawOval(x, y, width, height);

                  g.setColor(Color.WHITE);
                  g.fillOval(x + width - 6, y + height - 6, 12, 12);

                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.fillOval(x + width - 4, y + height - 4, 8, 8);
            }
            else {
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillOval(x, y, width, height);
            }
      }
      // private PlaceRectangleStrategy(Board board) {
      //       this.board = board;
      // }

      // public Strategy getInstance(Board board) {
      //       if(strategy == null){
      //             strategy = new PlaceRectangleStrategy(board);
      //       }
      //       return strategy;
      // }

      // public void execute(int x, int y, int width, int height) {
      //       this.shape = new Rectangle(x, y, width, height);
      //       Order place = new PlaceShapeCommand(this.shape);
      //       this.board.invoker.execute(place);
      // }
}