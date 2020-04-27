package commands;

import shapes.*;

public class SelectShapeCommand extends Order {

      public SelectShapeCommand(Shape shape){
            this.shape = shape;
      }

      public void execute(){
            this.shape.select();
      }

      public void undo(){
            this.shape.deselect();
      }

      public void redo(){
            this.shape.select();
      }
}