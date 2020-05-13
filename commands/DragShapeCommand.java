package commands;

import shapes.*;
import UI.Invoker;

public class DragShapeCommand extends Order {
      private Location location;

      public DragShapeCommand(BaseShape shape, Location location){
            this.shape = shape;
            this.location = location;
      }

      public void execute(){
            this.shape.dragCommand(this.location);
      }

      public void undo(){
            this.shape.undoDrag();
      }

      public void redo(){
            this.shape.redoDrag();
      }
}