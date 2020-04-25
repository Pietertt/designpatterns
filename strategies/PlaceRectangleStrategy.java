package strategies;

import commands.*;
import shapes.*;
import UI.commandInvoker;

public class PlaceRectangleStrategy extends Strategy {

      public PlaceRectangleStrategy(commandInvoker invoker) {
            this.invoker = invoker;
      }

      public void prepare(int x, int y, int width, int height) {
            this.shape = new Rectangle(x, y, width, height);
      }

      public void place() {
            Order place = new PlaceShapeCommand(this.shape);
            this.invoker.execute(place);
      }
}