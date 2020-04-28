package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;

public abstract class Strategy {
      protected Order order;
      protected Invoker invoker;
      protected Board board;
      public Shape shape;

      public abstract void prepare(int x, int y, int width, int height);
      public abstract void place();

}