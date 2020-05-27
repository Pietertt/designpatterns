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
           System.out.println("execute");
             undoStack.push(cmd);
             redoStack.clear();
             cmd.execute();
       }

       public void undo() {
             if (!undoStack.empty()) {
                   Order cmd = undoStack.pop();
                   cmd.undo();
                   redoStack.push(cmd);
             }
       }

     public void redo() {
            if(!redoStack.empty()) {
                Order cmd = redoStack.pop();
                cmd.redo();
                undoStack.push(cmd);
            }
     }
 }