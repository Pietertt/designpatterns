package IO;

import java.util.ArrayList;

import UI.Board;
import shapes.*;
import commands.*;

public class GetTriangle implements Operation {
      public BaseShape apply(ArrayList<String> lines, Board board){
            String[] line = lines.get(0).trim().split("\\s+");
            BaseShape shape = new Shape(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            shape.setStrategy(board.triangleStrategy);
            Order place = new PlaceShapeCommand(shape);
            board.invoker.execute(place);
            board.app.add(shape);
            board.app.revalidate();
            board.app.repaint();

            System.out.println(line[0]);

            return shape;
      }
}