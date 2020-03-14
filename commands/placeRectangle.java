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

    board board;

    public placeRectangle(Rectangle rectangle, Graphics2D g, board board) {
        this.rectangle = rectangle;
        this.rectangle.setBoard(board);
        undoStack.add(this.rectangle);
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
