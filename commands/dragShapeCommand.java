package commands;

import shapes.rectangle;

import java.util.Stack;

public class dragShapeCommand implements order {
    private rectangle shape;
    //private static Stack<rectangle> history = new Stack<>();

    public dragShapeCommand(rectangle shape) {
        //history = new Stack<>();
        this.shape = shape;
    }

    @Override
    public void execute() {
        //history.add(shape);
        shape.drag();
    }

    @Override
    public void undo() {
        //his.shape = history.pop();
        shape.undoDrag(/*shape*/);
    }
}
