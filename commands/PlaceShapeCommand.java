package commands;

import shapes.*;

public class PlaceShapeCommand implements Order {
      private Rectangle shape;
      
      public PlaceShapeCommand(Rectangle shape){
            this.shape = shape;
      }

      public void execute(){
            
      }

      public void undo(){

      }

      public void redo(){

      }
}