package strategies;

import commands.*;
import shapes.*;
import ui.commandInvoker;

public class PlaceRectangleStrategy extends Strategy {
      
      public PlaceRectangleStrategy(commandInvoker invoker){
            this.invoker = invoker;
      }

      public void prepare(int x, int y, int width, int height){
            this.rectangle = new rectangle(x, y, width, height, 1);
      }

      public void place(){
            placeShapeCommand place = new placeShapeCommand(this.rectangle);
            this.invoker.execute(place);
      }
}