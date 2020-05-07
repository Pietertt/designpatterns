package shapes;

import javax.swing.*;
import java.awt.*;

public abstract class ShapeDecorator extends JComponent implements Shape {
    protected BaseShape decoratedShape;

    public ShapeDecorator(BaseShape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void place() {
        decoratedShape.place();
    }

}
