package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;

public class PlaceEllipseStrategy extends Strategy {

      public PlaceEllipseStrategy(Invoker invoker, Board board) {
            this.invoker = invoker;
            this.board = board;
      }

      public void prepare(int x, int y, int width, int height) {
            this.shape = new Ellipse(x, y, width, height);
      }

      public void place() {
            PlaceShapeCommand place = new PlaceShapeCommand(this.shape, this.invoker, this.board);
            this.invoker.execute(place);
      }
}