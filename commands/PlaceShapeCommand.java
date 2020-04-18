package commands;

import shapes.*;

public class PlaceShapeCommand implements Order {
      private Shape shape;

      public PlaceShapeCommand(Shape shape){
            this.shape = shape;
      }

      public void execute(){
          
      }

      public Shape getShape(){
            return this.shape;
      }

      public void undo(){
            
      }

      public void redo(){
            
      }
}