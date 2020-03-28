package commands;

import shapes.*;

public class selectShapeCommand implements order {
      private shape shape;

      public selectShapeCommand(shape shape){
            this.shape = shape;
      }

      public void execute(){
            this.shape.select();
      }

      @Override
      public void undo() {

      }
}