package strategies;

import commands.*;
import shapes.*;
import UI.Board;
import UI.Invoker;
import visitor.moveVisitor;

public class PlaceRectangleStrategy extends Strategy {

      public PlaceRectangleStrategy(Invoker invoker, Board board) {
            this.invoker = invoker;
            this.board = board;
      }

      public void place(int x, int y, int width, int height) {

            this.shape = new Rectangle(x, y, width, height);

            Order place = new PlaceShapeCommand(this.shape);
            this.invoker.execute(place);
      }
}