package commands;

import shapes.Rectangle;
import ui.board;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class placeRectangle implements Order {
    private shapes.Rectangle rectangle;
    JFrame canvas;

    private static final Stack<shapes.Rectangle> undoStack = new Stack<>();
    private static final Stack<shapes.Rectangle> redoStack = new Stack<>();

    Graphics2D g;

    public placeRectangle(Rectangle rectangle, JFrame canvas, Graphics2D g) {
        this.rectangle = rectangle;
        undoStack.add(this.rectangle);
        this.canvas = canvas;
        this.g = g;
    }

    public placeRectangle() {

    }

    @Override
    public void execute() {
        rectangle.paintComponent(g);
    }

    @Override
    public void undo() {
        rectangle.undoButtonClicked();
    }

    @Override
    public void redo() {
        rectangle.redoButtonClicked();
    }
}
