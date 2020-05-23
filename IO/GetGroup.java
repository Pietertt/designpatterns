package IO;

import UI.Board;
import shapes.*;
import java.util.ArrayList;
import commands.*;

public class GetGroup implements Operation {
      private TextShapeDecorator decorator = null;

      private String previousLine = "nothing";

      public BaseShape apply(ArrayList<String> lines, Board board){
            BaseShape shape = new Group(board);
            shape.setStrategy(board.groupStrategy);

            String[] line = lines.get(0).trim().split("\\s+");
            int count = Integer.parseInt(line[1]);



            System.out.println(line[0] + " " + count);



            lines.remove(0);

            while(lines.size() > 0){
                  String[] l = lines.get(0).trim().split("\\s+");
                  if(l[0].equals("ornament")) {
                        if(!previousLine.equals("ornament")) {
                              decorator = new TextShapeDecorator();
                        }
                        if(l[1].equals("top")) {
                              decorator.setTop(l[2]);
                        }
                        if(l[1].equals("bottom")) {
                              decorator.setBottom(l[2]);
                        }
                        if(l[1].equals("left")) {
                              decorator.setLeft(l[2]);
                        }
                        if(l[1].equals("right")) {
                              decorator.setRight(l[2]);
                        }
                  } else {
                        if(previousLine.equals("ornament") && (l[0].equals("group"))) {
                              decorator.setDecoratedShape(shape);
                              shape.add(decorator);
                        }

                        Operation target = Factory.getOperation(l[0]);
                        BaseShape appliedTarget = target.apply(lines, board);

                        if(previousLine.equals("ornament") && !(l[0].equals("group"))) {
                              decorator.setDecoratedShape(appliedTarget);
                              appliedTarget.add(decorator);
                        }
                        shape.children.add(appliedTarget);
                  }

                  previousLine = l[0];

                  if(!l[0].equals("ornament")) {
                        decorator = null;
                  }

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