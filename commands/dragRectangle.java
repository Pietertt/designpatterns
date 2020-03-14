package commands;

import shapes.Rectangle;
import ui.ui;

public class dragRectangle implements Order {
    private Rectangle rectangle;
    private int mode;

    public dragRectangle(Rectangle rectangle, int mode) {
        this.rectangle = rectangle;
        this.mode = mode;
    }

    @Override
    public void execute() {
        rectangle.update(mode);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
