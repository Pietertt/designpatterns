package commands;

import shapes.rectangle;

public class dragShapeCommand implements order {
    private rectangle shape;

    public dragShapeCommand(rectangle shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        //shape.dragIsOn();
    }

    @Override
    public void undo() {
        //shape.dragIsOff();
    }
}
