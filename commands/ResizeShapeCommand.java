package commands;

import shapes.*;
import UI.Invoker;

public class ResizeShapeCommand extends Order {
      private Location location;

      public ResizeShapeCommand(Shape shape, Location location){
            this.shape = shape;
            this.location = location;
      }

      public void execute(){
            this.shape.drag(this.location);
      }

      public void undo(){
            this.shape.undoDrag();
      }

      public void redo(){
            this.shape.redoDrag();
      }
}