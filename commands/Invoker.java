package commands;

import java.util.Stack;
import shapes.*;

public class Invoker {
      private Stack<Command> redo = new Stack<Command>();
      private Stack<Command> undo = new Stack<Command>();

      public Invoker(){

      }

      public Shape execute(Command command){
            undo.push(command);
            redo.clear();
            return command.get();
      }


}