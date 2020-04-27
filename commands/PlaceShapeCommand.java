package commands;

import shapes.*;
import UI.Invoker;

public class PlaceShapeCommand extends Order {
      private Invoker invoker;

      public PlaceShapeCommand(Shape shape, Invoker invoker){
            this.shape = shape;
            this.invoker = invoker;
      }

      public void execute(){
            this.shape.place(this.invoker);
      }

      public void undo(){
            this.shape.remove();
      }

      public void redo(){
            this.shape.place(this.invoker);
      }
}