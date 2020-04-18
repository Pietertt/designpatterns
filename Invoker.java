import java.util.Stack;

import commands.Order;

public class Invoker {
      public Stack<Order> undoStack = new Stack<Order>();
      public Stack<Order> redoStack = new Stack<Order>();

      public void execute(Order order) {
            this.undoStack.push(order);
            this.redoStack.clear();
            order.execute();
      }

      public void undo() {
            if (!this.undoStack.isEmpty()) {
                  Order order = this.undoStack.pop();
                  order.undo();
                  this.redoStack.push(order);
            }
      }

      public void redo() {
            if(!this.redoStack.isEmpty()){
                  Order order = this.redoStack.pop();
                  order.redo();
                  this.undoStack.push(order);
            }
      }
}