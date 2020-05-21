package IO;

import UI.Board;
import shapes.*;
import java.util.ArrayList;

public class GetRectangle implements Operation {
      public BaseShape apply(ArrayList<String> lines, Board board){
            String[] line = lines.get(0).trim().split("\\s+");
            BaseShape shape = new Shape(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            shape.setStrategy(board.rectangleStrategy);

            return shape;
      }
}