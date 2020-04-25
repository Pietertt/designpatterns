package commands;

import shapes.*;

public class placeShapeCommand implements order {
    public Shape shape;

    public placeShapeCommand(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape.setDrawTrue();
    }

    @Override
    public void undo() {
        shape.setDrawFalse();
    }

    @Override
    public void redo() {
        shape.setDrawTrue();
    }
}
