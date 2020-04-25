package commands;

import shapes.*;

public class SelectShapeCommand extends Order {

      public SelectShapeCommand(Shape shape){
            this.shape = shape;
      }

      public void execute(){
               //this.shape.select();
      }

      public void undo(){

      }

      public void redo(){

      }
}