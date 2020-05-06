package commands;

import shapes.*;
import java.awt.event.*;

public class DeselectShapeCommand extends Order {
      private MouseEvent event;

      public DeselectShapeCommand(BaseShape shape, MouseEvent event){
            this.shape = shape;
            this.event = event;
      }

      public void execute(){
            this.shape.deselect(this.event);
      }

      public void undo(){
            this.shape.select(this.event);
      }

      public void redo(){
            this.shape.deselect(this.event);
      }
}