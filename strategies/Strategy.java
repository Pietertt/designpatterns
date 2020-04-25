package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;

public abstract class Strategy {
      protected Order order;
      protected Invoker invoker;
      public Shape shape;

      public abstract void prepare(int x, int y, int width, int height);
      public abstract void place();

}