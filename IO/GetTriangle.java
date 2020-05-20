package IO;

import UI.Board;
import shapes.*;

public class GetTriangle implements Operation {
      public BaseShape apply(Location location, Board board){
            BaseShape shape = new Shape(location.x, location.y, location.width, location.height);
            shape.setStrategy(board.triangleStrategy);

            return shape;
      }
}