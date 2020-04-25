package commands;

import shapes.*;

public abstract class Order {
      public Shape shape;

      public abstract void execute();
      public abstract void undo();
      public abstract void redo();
}