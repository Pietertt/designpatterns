package IO;

import UI.Board;
import shapes.*;
import java.util.ArrayList;
import commands.*;

public class GetGroup implements Operation {
      public BaseShape apply(ArrayList<String> lines, Board board){
            BaseShape shape = new Group(board);
            shape.setStrategy(board.groupStrategy);

            String[] line = lines.get(0).trim().split("\\s+");
            int count = Integer.parseInt(line[1]);


            System.out.println(line[0] + " " + count);
            lines.remove(0);

            while(lines.size() > 0){
                  String[] l = lines.get(0).trim().split("\\s+");
                  Operation target = Factory.getOperation(l[0]);
                  shape.children.add(target.apply(lines, board));
                  if(lines.size() > 0){
                        lines.remove(0);
                  }
            }

            Order place = new PlaceShapeCommand(shape);
            board.invoker.execute(place);
            board.app.add(shape);
            board.app.revalidate();
            board.app.repaint();

            return shape;

            // for(int i = 0; i < count; i++){
            //       String[] l = lines.get(i).trim().split("\\s+");
            //       System.out.println(l[0]);
            //       lines.remove(i);
            // }
      }
}