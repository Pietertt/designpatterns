package IO;

import UI.Board;
import commands.*;
import shapes.*;
import java.util.ArrayList;

public class GetEllipse implements Operation {
      public BaseShape apply(ArrayList<String> lines, Board board){
            String[] line = lines.get(0).trim().split("\\s+");
            BaseShape shape = new Shape(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            Order place = new PlaceShapeCommand(shape);
            board.invoker.execute(place);
            board.app.add(shape);
            board.app.revalidate();
            board.app.repaint();
            

            shape.setStrategy(board.ellipseStrategy);

            return shape;
      }
}