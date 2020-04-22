package commands;

import shapes.*;

public class SelectShapeCommand implements Order {
      private Rectangle shape;

      public SelectShapeCommand(Rectangle shape){
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