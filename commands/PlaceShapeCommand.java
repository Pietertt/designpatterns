package commands;

import shapes.*;
import UI.Invoker;
import UI.Board;

public class PlaceShapeCommand extends Order {
      public PlaceShapeCommand(BaseShape shape){
            this.shape = shape;
      }

      public void execute(){
            this.shape.place();
      }

      public void undo(){
            this.shape.remove();
      }

      public void redo(){
            this.shape.place();
      }
}