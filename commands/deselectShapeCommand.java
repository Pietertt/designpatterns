package commands;

import shapes.rectangle;

public class deselectShapeCommand implements order {
    private rectangle shape;

    public deselectShapeCommand(rectangle shape) {
        this.shape = shape;
    }

    public void execute(){
        shape.setSelectedFalse();
    }

    @Override
    public void undo() {
        shape.setSelectedTrue();
    }
}
