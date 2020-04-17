package commands;

import shapes.*;

public class DeselectShapeCommand implements Order {
      private Shape shape;

      public DeselectShapeCommand(Shape shape){
            this.shape = shape;
      }

      public void execute(){
            this.shape.deselect();
      }

      public void undo(){

      }

      public void redo(){

      }
}