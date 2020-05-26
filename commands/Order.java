package commands;

import shapes.*;

// Standaard commando's die uitgevoerd kunnen worden
public abstract class Order {
      public Shape shape;

      public abstract void execute();
      public abstract void undo();
      public abstract void redo();
}