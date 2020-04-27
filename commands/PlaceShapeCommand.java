package commands;

import shapes.*;

public class PlaceShapeCommand extends Order {

      public PlaceShapeCommand(Shape shape){
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