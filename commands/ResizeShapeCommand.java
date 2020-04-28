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
            this.shape.resize(this.location);
      }

      public void undo(){
            this.shape.undoResize();
      }

      public void redo(){
            this.shape.redoResize();
      }
}