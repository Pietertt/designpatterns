package commands;

import shapes.*;
import java.awt.event.*;

public class SelectShapeCommand extends Order {
      private MouseEvent event;

      public SelectShapeCommand(BaseShape shape){
            this.shape = shape;
            this.event = event;
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