package ui;


import commands.order;

import java.util.Stack;

public class commandInvoker {
    private final Stack<order> undoStack;
    private final Stack<order> redoStack;

    public commandInvoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void execute(order cmd) {
        undoStack.push(cmd);
        redoStack.clear();
        cmd.execute();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            order cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    public void redo() {
        order cmd = redoStack.pop();
        cmd.execute();
        undoStack.push(cmd);
    }
}
