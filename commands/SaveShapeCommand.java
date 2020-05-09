package commands;

import shapes.*;
import UI.Invoker;

public class SaveShapeCommand extends Order {
      private Location location;

      public SaveShapeCommand(BaseShape shape, Location location){
            this.shape = shape;
            this.location = location;
      }

      public void execute(){
            this.shape.save(this.location);
      }

      public void undo(){
            this.shape.undoDrag();
      }

      public void redo(){
            this.shape.redoDrag();
      }
}