package commands;

import shapes.Rectangle;
import ui.ui;

import java.awt.*;
import java.util.Stack;

public class dragRectangle implements Order {
    private String orderName = "drag";

    private Rectangle rectangle;
    private int mode;

    private static final Stack<Rectangle> undoStack = new Stack<>();
    private static final Stack<Rectangle> redoStack = new Stack<>();

    Graphics2D g;

    public dragRectangle(Rectangle rectangle, int mode) {
        this.rectangle = rectangle;
        this.mode = mode;
        undoStack.add(this.rectangle);
    }

    @Override
    public void addGraphics(Graphics2D g) {
        this.g = g;
    }

    @Override
    public String getName() {
        return orderName;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void execute() {
        rectangle.update(mode);
    }

    @Override
    public void undo() {
        this.rectangle = undoStack.pop();
        redoStack.add(rectangle);
        rectangle.undoButtonClicked(rectangle,true);
    }

    @Override
    public void redo() {
        this.rectangle = redoStack.pop();
        undoStack.add(rectangle);
        rectangle.redoButtonClicked(rectangle,true);
    }
}
