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

       // 
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