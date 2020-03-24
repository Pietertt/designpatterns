package commands;

import shapes.Rectangle;
import shapes.Shape;

public class createShapeCommand implements order {
      private Shape shape;

      public createShapeCommand(Shape shape){
            this.shape = shape;
      }

      @Override
      public void execute(){
            this.shape.create();
      }
}