package commands;

import shapes.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class selectRectangle implements Order {
    private String orderName = "select";

    private Rectangle rectangle;
    private MouseEvent e;

    private static final Stack<Rectangle> undoStack = new Stack<>();
    private static final Stack<shapes.Rectangle> redoStack = new Stack<>();

    Graphics2D g;

    public selectRectangle(Rectangle rectangle, MouseEvent e) {
        this.rectangle = rectangle;
        this.e = e;
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
        rectangle.selectionHandler(e);
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
