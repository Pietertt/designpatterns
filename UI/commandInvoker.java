package UI;

 import commands.Order;

 import java.util.Stack;

 public class commandInvoker {
       private final Stack<Order> undoStack;
       private final Stack<Order> redoStack;

       public commandInvoker() {
             undoStack = new Stack<>();
             redoStack = new Stack<>();
       }

       public void execute(Order cmd) {
             undoStack.push(cmd);
             redoStack.clear();
             cmd.execute();
       }

       public void undo() {
             if (!undoStack.isEmpty()) {
                   Order cmd = undoStack.pop();
                   cmd.undo();
                   redoStack.push(cmd);
             }
       }

     public void redo() {
       if(!redoStack.isEmpty()) {
             Order cmd = redoStack.pop();
             cmd.redo();
             undoStack.push(cmd);
       }
     }
 }