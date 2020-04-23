package commands;

import shapes.*;

public class deselectShapeCommand implements order {
    private Shape shape;

    public deselectShapeCommand(Shape shape) {
        this.shape = shape;
    }

    public void execute(){
        shape.setSelectedFalse();
    }

    @Override
    public void undo() {
        shape.setSelectedTrue();
    }

    @Override
    public void redo() {
        shape.setSelectedFalse();
    }
}
