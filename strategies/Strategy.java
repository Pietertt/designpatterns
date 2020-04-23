package strategies;

import commands.*;
import shapes.*;
import ui.commandInvoker;

public abstract class Strategy {
      protected order order;
      protected commandInvoker invoker;
      public shape rectangle;

      public abstract void prepare(int x, int y, int width, int height);
      public abstract void place();

}