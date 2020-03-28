package commands;

import shapes.*;

public class placeShapeCommand implements order {
    private rectangle shape;

    public placeShapeCommand(rectangle shape) {
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

    public shape getShape() {
        return shape;
    }
}
