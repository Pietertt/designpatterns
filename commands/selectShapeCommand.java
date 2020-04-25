package commands;

import shapes.*;

public class selectShapeCommand implements order {
      private shape shape;

      public selectShapeCommand(shape shape) {
            this.shape = shape;
      }

      public void execute(){
            shape.setSelectedTrue();
      }

      @Override
      public void undo() {
            shape.setSelectedFalse();
      }

      @Override
      public void redo() {
            shape.setSelectedTrue();
      }
}