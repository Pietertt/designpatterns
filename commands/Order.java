package commands;

import shapes.Rectangle;

import java.awt.*;

public interface Order {
    String getName();
    Rectangle getRectangle();
    void execute();
    void undo();
    void redo();
    void addGraphics(Graphics2D g);
}
