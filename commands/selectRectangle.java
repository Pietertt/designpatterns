package commands;

import shapes.Rectangle;

import java.awt.event.MouseEvent;

public class selectRectangle implements Order {
    private Rectangle rectangle;
    private MouseEvent e;

    public selectRectangle(Rectangle rectangle, MouseEvent e) {
        this.rectangle = rectangle;
        this.e = e;
    }

    @Override
    public void execute() {
        rectangle.selectionHandler(e);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
