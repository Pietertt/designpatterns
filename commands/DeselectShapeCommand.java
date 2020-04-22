package commands;

import shapes.*;

public class DeselectShapeCommand implements Order {
      private Rectangle shape;

      public DeselectShapeCommand(Rectangle shape){
            this.shape = shape;
      }

      public void execute(){
            this.shape.deselect();
      }

      public void undo(){
            this.shape.select();
      }

      public void redo(){
            this.shape.deselect();
      }
}