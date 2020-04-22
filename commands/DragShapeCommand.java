package commands;

import shapes.*;

public class DragShapeCommand implements Order {
      private Shape shape;

      public DragShapeCommand(Shape shape){
            this.shape = shape;
      }

      public void execute(){
            this.shape.drag();
      }

      public void undo(){
            
      }

      public void redo(){
            
      }
}