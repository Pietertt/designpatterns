package commands;

import shapes.*;

public class resizeShapeCommand implements order {
      private Shape shape;

      public resizeShapeCommand(Shape shape) {
            this.shape = shape;
      }

      public void execute(){
            //this.shape.change(int width, int height);
      }

      @Override
      public void undo() {
      //      shape.setSelectedFalse();
      }

      @Override
      public void redo() {
    //        shape.setSelectedTrue();
      }
}