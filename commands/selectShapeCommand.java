package commands;

import shapes.*;

public class selectShapeCommand implements order {
      private rectangle shape;

      public selectShapeCommand(rectangle shape) {
            this.shape = shape;
      }

      public void execute(){
            shape.setSelectedTrue();
      }

      @Override
      public void undo() {
            shape.setSelectedFalse();
      }
}