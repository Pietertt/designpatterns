package IO;

import UI.Board;
import shapes.*;

public interface Operation {
      public BaseShape apply(Location location, Board board);
}