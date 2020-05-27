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

      // Executes the command
      public void execute(Order cmd) {
            // Push the command on the undo stack
            undoStack.push(cmd);
            // Clear the redo stack
            redoStack.clear();
            // Execute the command
            cmd.execute();
      }

      public void undo() {
            if (!undoStack.empty()) {
                  // Pops an item off the undo stack
                  Order cmd = undoStack.pop();
                  // Undos the popped item
                  cmd.undo();
                  // Push the popped item on the redo stack
                  redoStack.push(cmd);
            }
      }

      public void redo() {
            if (!redoStack.empty()) {
                  // Pop an item off the redo stack
                  Order cmd = redoStack.pop();
                  // Redos the popped item
                  cmd.redo();
                  // Push the popped item on the undo stack
                  undoStack.push(cmd);
            }
      }
}