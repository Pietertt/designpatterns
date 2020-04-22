package commands;

import shapes.*;

public class DragShapeCommand implements Order {
      private Rectangle shape;

      public DragShapeCommand(Rectangle shape){
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