package commands;

import shapes.*;
import java.awt.event.*;

// Selecteren van een shape aan de hand van commands
public class SelectShapeCommand extends Order {
      private MouseEvent event;

      public SelectShapeCommand(BaseShape shape, MouseEvent event){
            this.shape = shape;
            this.event = event;
      }

      public void execute(){
            this.shape.select(this.event);
      }

      public void undo(){
            this.shape.deselect(this.event);
      }

      public void redo(){
            this.shape.select(this.event);
      }
}