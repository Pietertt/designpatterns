package commands;

import shapes.*;

// Standaard orders(commands) die uit te voeren zijn
public abstract class Order {
      public BaseShape shape;

      public abstract void execute();
      public abstract void undo();
      public abstract void redo();
}