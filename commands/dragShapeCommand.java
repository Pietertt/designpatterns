package commands;

import shapes.*;

import java.util.Stack;

public class dragShapeCommand implements order {
    private shape shape;
    //private static Stack<rectangle> history = new Stack<>();

    // TODO try fixing redo with an arraylist(or something like 2 stacks?) Lets say  you have 1 2 3, With undo you go back to state 2,
    //  with execute back to state 3,
    // TODO if there is no state 4, you add it to the arraylist.
    public dragShapeCommand(shape shape) {
        //history = new Stack<>();
        this.shape = shape;
    }

    @Override
    public void execute() {
        //history.push(shape);
        shape.drag();
    }

    @Override
    public void undo() {
        shape.undoDrag();
    }

    @Override
    public void redo() {
        shape.redoDrag();
    }
}
