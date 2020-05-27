package UI;

import commands.Order;

import java.util.Stack;

public class Invoker {
      private final Stack<Order> undoStack;
      private final Stack<Order> redoStack;

      public Invoker() {
            this.undoStack = new Stack<>();
            this.redoStack = new Stack<>();

      }

      public void execute(Order cmd) {
            // Pushes an order on the undo stack
            undoStack.push(cmd);
            // Clears the redo stack
            redoStack.clear();
            // Executes the command
            cmd.execute();
      }

      public void undo() {
            // Undo is only possible when a history exists
            if (!undoStack.isEmpty()) {
                  // Pops an order of the undo stack and executes it
                  // the corresponding order is added to the redo stack
                  Order cmd = undoStack.pop();
                  // Redos the order
                  cmd.undo();
                  redoStack.push(cmd);
            }
      }

      public void redo() {
            // Redo is only possible when a history exists
            if (!redoStack.isEmpty()) {
                  // Pops an order of the redo stacks and executes it
                  // the corresponding order is added to the undo stack
                  Order cmd = redoStack.pop();
                  // Undos the order
                  cmd.redo();
                  undoStack.push(cmd);
            }
      }
}