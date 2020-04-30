package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;

public abstract class Strategy {
      protected Order order;
      protected Invoker invoker;
      protected Board board;
      public BaseShape shape;

      public abstract void place(int x, int y, int width, int height);

}