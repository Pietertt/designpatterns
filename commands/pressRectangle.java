package commands;

import shapes.Rectangle;

import java.awt.event.MouseEvent;

public class pressRectangle implements Order {
    private Rectangle rectangle;
    private MouseEvent e;

    public pressRectangle(Rectangle rectangle, MouseEvent e) {
        this.rectangle = rectangle;
        this.e = e;
    }

    @Override
    public void execute() {
        rectangle.pressedHandler(e);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
