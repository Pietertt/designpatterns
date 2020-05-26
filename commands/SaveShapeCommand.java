package commands;

import shapes.*;
import UI.Invoker;

// Het opslaan van een shape aan de hand van commands
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