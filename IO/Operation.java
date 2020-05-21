package IO;

import UI.Board;
import shapes.*;
import java.util.ArrayList;

public interface Operation {
      public BaseShape apply(ArrayList<String> lines, Board board);
}