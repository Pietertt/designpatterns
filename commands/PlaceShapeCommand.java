package commands;

import shapes.*;

public class PlaceShapeCommand extends Order {

      public PlaceShapeCommand(Rectangle shape){
            this.shape = shape;
      }

      public void execute(){
               this.shape.place();
      }

      public void undo(){

      }

      public void redo(){

      }
}