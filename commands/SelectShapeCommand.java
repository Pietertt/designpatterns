package commands;

import shapes.*;
import java.awt.event.*;

// Het selecteren van een shape commando
public class SelectShapeCommand extends Order {
      private MouseEvent event;

      public SelectShapeCommand(Shape shape, MouseEvent event){
            this.shape = shape;
            this.event = event;
      }

      public void execute(){
            this.shape.select(this.event);
      }

      public void undo(){
            this.shape.deselect();
      }

      public void redo(){
            this.shape.select(this.event);
      }
}