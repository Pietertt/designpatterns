package IO;

import shapes.*;

public class GetRectangle implements Operation {
      public BaseShape apply(Location location){
            BaseShape shape = new Shape(location.x, location.y, location.width, location.height);

            return shape;
      }
}