package UI;

 import commands.Order;

 import java.util.Stack;

 public class Invoker {
     // Undo's en Redo's worden opgeslagen in een stack zodat deze teruggehaald kunnen worden
       private final Stack<Order> undoStack;
       private final Stack<Order> redoStack;

       public Invoker() {
            this.undoStack = new Stack<>();
            this.redoStack = new Stack<>();

       }

       /*
       Het commando word uitgevoerd en aan de undostack toegevoegd,
       zodat deze teruggehaald kan worden als de gebruiker dit wenst
        */
       public void execute(Order cmd) {
             undoStack.push(cmd);
             redoStack.clear();
             cmd.execute();
       }

       /*
       De bovenste die op de Stack ligt wordt er met pop() vanaf gehaald,
       deze wordt vervolgens uitgevoerd en een de redoStack toegevoegd
        */
       public void undo() {
             if (!undoStack.isEmpty()) {
                   Order cmd = undoStack.pop();
                   cmd.undo();
                   redoStack.push(cmd);
             }
       }

       /*
       De bovenste wordt van de Stack gehaald met de pop() methode,
       deze wordt vervolgens uitgevoerd en weer aan de undoStack toegevoegd.
        */
     public void redo() {
       if(!redoStack.isEmpty()) {
             Order cmd = redoStack.pop();
             cmd.redo();
             undoStack.push(cmd);
       }
     }
 }