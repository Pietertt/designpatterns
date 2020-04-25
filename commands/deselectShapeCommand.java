package commands;

import shapes.*;

public class deselectShapeCommand implements order {
    private shape shape;

    public deselectShapeCommand(shape shape) {
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
