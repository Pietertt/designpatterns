package strategies;

import commands.*;
import shapes.*;
import ui.commandInvoker;

public class PlaceRectangleStrategy {
      private order order;
      private commandInvoker invoker;
      public rectangle rectangle;

      public PlaceRectangleStrategy(commandInvoker invoker){
            this.invoker = invoker;
      }

      public void prepare(int x, int y, int width, int height){
            this.rectangle = new rectangle(x, y, width, height, 1);
      }

      public void place(){
            System.out.println("Placed");
            placeShapeCommand place = new placeShapeCommand(this.rectangle);
            this.invoker.execute(place);
      }
}