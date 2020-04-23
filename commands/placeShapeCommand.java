package commands;

import shapes.*;

public class placeShapeCommand implements order {
    private shape shape;

    public placeShapeCommand(shape shape) {
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

    public shape getShape() {
        return shape;
    }
}
