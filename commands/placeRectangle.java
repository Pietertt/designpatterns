package commands;

import ui.board;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class placeRectangle implements Order {
    private Rectangle rectangle;
    JFrame canvas;

    private static final Stack<Rectangle> undoStack = new Stack<>();
    private static final Stack<Rectangle> redoStack = new Stack<>();

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
        //jpanel.add(new Rectangle(rectangle.x, rectangle.y,rectangle.width,rectangle.height,rectangle.id,rectangle.rgb));
        rectangle.paintComponent(g);
        //canvas.add(rectangle, BorderLayout.CENTER);
        //rectangle.repaint();
        //canvas.repaint();
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
