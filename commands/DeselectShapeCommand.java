package commands;

import shapes.*;
import java.awt.event.*;

public class DeselectShapeCommand extends Order {
      private MouseEvent event;

      public DeselectShapeCommand(BaseShape shape){
            this.shape = shape;
            this.event = event;
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