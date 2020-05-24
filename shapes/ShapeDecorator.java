package shapes;

import javax.swing.*;
import java.awt.*;

public abstract class ShapeDecorator extends BaseShape {
    protected BaseShape decoratedShape;

    public ShapeDecorator(BaseShape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public ShapeDecorator() {

    }

    public void place() {
        decoratedShape.place();
    }

    public void setDecoratedShape(BaseShape shape) {
        this.decoratedShape = shape;
    }

}
