package IO;

import UI.Board;
import commands.*;
import shapes.*;
import java.util.ArrayList;

public class GetTriangle implements Operation {
      public BaseShape apply(ArrayList<String> lines, Board board){
            // Trims the first line of any white space
            String[] line = lines.get(0).trim().split("\\s+");
            // Defines a baseshape
            BaseShape shape = new Shape(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            
            // Sets a strategy, places the shape and  adds it to the board
            shape.setStrategy(board.triangleStrategy);
            Order place = new PlaceShapeCommand(shape);
            board.invoker.execute(place);
            board.app.add(shape);
            board.app.revalidate();
            board.app.repaint();

            return shape;
      }
}