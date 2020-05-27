package IO;

import UI.Board;
import shapes.*;
import java.util.ArrayList;
import commands.*;

public class GetGroup implements Operation {
      public String previousLine = "nothing";

      public BaseShape apply(ArrayList<String> lines, Board board){

            // Defines a new group and set the corresponding strategy
            BaseShape shape = new Group(board);
            shape.setStrategy(board.groupStrategy);

            // Trims the first line of any spaces
            String[] line = lines.get(0).trim().split("\\s+");
            // Retrieves the amount of children the group has
            int count = Integer.parseInt(line[1]);

            // Removes the first line
            lines.remove(0);

            while(lines.size() > 0){
                  // Trims the first line of all blank characters
                  String[] l = lines.get(0).trim().split("\\s+");
            
                  // Gets the right Operation based on the first element 
                  Operation target = Factory.getOperation(l[0]);
                  // Executes the operation
                  BaseShape appliedTarget = target.apply(lines, board);


                  // Adds the retrieved BaseShape to the children of the group
                  shape.children.add(appliedTarget);

                  previousLine = l[0];

                  // Removes the first line if there is any
                  if(lines.size() > 0){
                        lines.remove(0);
                  }
            }

            // Places the group and adds it to the board
            Order place = new PlaceShapeCommand(shape);
            place.execute();
            //board.invoker.execute(place);
            board.app.add(shape);
            board.app.revalidate();
            board.app.repaint();

            return shape;
      }
}