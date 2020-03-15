package commands;

import shapes.Rectangle;
import ui.board;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class placeRectangle implements Order {
    private String orderName = "place";

    private Rectangle rectangle;
    JFrame canvas;

    private static final Stack<shapes.Rectangle> undoStack = new Stack<>();
    private static final Stack<shapes.Rectangle> redoStack = new Stack<>();

    Graphics2D g;

    board board;

    public placeRectangle(Rectangle rectangle, board board) {
        this.rectangle = rectangle;
        this.rectangle.setBoard(board);
        undoStack.add(this.rectangle);
        //this.g = g;
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
        rectangle.paintComponent(g);
        //rectangle.repaint();
    }

    @Override
    public void undo() {
        this.rectangle = undoStack.pop();
        redoStack.add(rectangle);
        rectangle.undoButtonClicked(rectangle,false);
    }

    @Override
    public void redo() {
        this.rectangle = redoStack.pop();
        undoStack.add(rectangle);
        rectangle.redoButtonClicked(rectangle,true);
    }
}
