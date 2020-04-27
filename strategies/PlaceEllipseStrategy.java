package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;

public class PlaceEllipseStrategy extends Strategy {

      public PlaceEllipseStrategy(Invoker invoker) {
            this.invoker = invoker;
      }

      public void prepare(int x, int y, int width, int height) {
            this.shape = new Ellipse(x, y, width, height);
      }

      public void place() {
            PlaceShapeCommand place = new PlaceShapeCommand(this.shape, this.invoker);
            this.invoker.execute(place);
      }
}