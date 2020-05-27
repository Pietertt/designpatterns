package IO;

import UI.Board;
import shapes.*;
import java.util.ArrayList;
import commands.*;

public class GetGroup implements Operation {
      public TextShapeDecorator decorator = null;

      public String previousLine = "nothing";

      public BaseShape apply(ArrayList<String> lines, Board board){

            // Defines a new group and set the corresponding strategy
            BaseShape shape = new Group(board);
            shape.setStrategy(board.groupStrategy);

            // Trims the first line of any spaces
            String[] line = lines.get(0).trim().split("\\s+");
            // Retrieves the amount of children the group has
            int count = Integer.parseInt(line[1]);


            // Adds a new decorator to the group
            if(decorator != null) {
                  previousLine = "group";
                  TextShapeDecorator newGroupDecorator = decorator;
                  newGroupDecorator.setDecoratedShape(shape);
                  shape.add(newGroupDecorator);
            }

            // Removes the first line
            lines.remove(0);

            while(lines.size() > 0){
                  // Trims the first line of all blank characters
                  String[] l = lines.get(0).trim().split("\\s+");

                  // Filling the text of the ornament
                  if(l[0].equals("ornament")) {
                        if(!previousLine.equals("ornament")) {
                              decorator = new TextShapeDecorator();
                              System.out.println("Test");

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
                        // Gets the right Operation based on the first element 
                        Operation target = Factory.getOperation(l[0]);
                        // Executes the operation
                        BaseShape appliedTarget = target.apply(lines, board);

                        // Adds an ornament
                        if(previousLine.equals("ornament")) {
                              decorator.setDecoratedShape(appliedTarget);
                              appliedTarget.add(decorator);
                              shape.children.add(decorator);
                        }

                        // Adds the retrieved BaseShape to the children of the group
                        shape.children.add(appliedTarget);

                  }

                  previousLine = l[0];

                  if(!l[0].equals("ornament")) {
                        decorator = null;
                  }

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