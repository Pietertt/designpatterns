package strategies;

import commands.*;
import shapes.*;
import UI.Board;
import UI.Invoker;

public class PlaceRectangleStrategy extends Strategy {

      public PlaceRectangleStrategy(Invoker invoker, Board board) {
            this.invoker = invoker;
            this.board = board;
      }

      public void prepare(int x, int y, int width, int height) {
            this.shape = new Rectangle(x, y, width, height);
      }

      public void place() {
            Order place = new PlaceShapeCommand(this.shape, this.invoker, this.board);
            this.invoker.execute(place);
      }
}