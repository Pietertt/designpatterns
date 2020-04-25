package commands;

import shapes.*;

public abstract class Order {
      public Rectangle shape;

      public abstract void execute();
      public abstract void undo();
      public abstract void redo();
}