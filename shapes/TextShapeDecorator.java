package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class TextShapeDecorator extends ShapeDecorator {

    public String bottom = "";
    public String top = "";
    public String left = "";
    public String right = "";

    public TextShapeDecorator(BaseShape decoratedShape, String bottom, String top, String left, String right) {
        super(decoratedShape);
        this.bottom = bottom;
        this.top = top;
        this.left = left;
        this.right = right;
    }

    @Override
    public void place() {
        decoratedShape.place();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(decoratedShape.selected) {

            Graphics2D g2d = (Graphics2D) g;

            // Ornament at bottom of component
            g2d.drawString(bottom, decoratedShape.x, decoratedShape.y + decoratedShape.height + 10);

            // Ornament at top of component
            g2d.drawString(top, decoratedShape.x, decoratedShape.y - 2);

            // Ornament at left side of component
            verticalLeft(g2d, left);

            // Ornament at right side of component
            verticalRight(g2d, right);
        }
    }

    private void verticalLeft(Graphics2D g2d, String str) {
        int position = decoratedShape.y + 5;
        for (int i = 0; i < str.length(); i++) {
            System.out.println();
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 9));
            g2d.drawString(String.valueOf(str.charAt(i)),decoratedShape.x - 10, position);
            position += 7;
        }
    }

    private void verticalRight(Graphics2D g2d, String str) {
        int position = decoratedShape.y + 5;
        for (int i = 0; i < str.length(); i++) {
            System.out.println();
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 9));
            g2d.drawString(String.valueOf(str.charAt(i)),decoratedShape.x + decoratedShape.width + 5, position);
            position += 7;
        }
    }

    @Override
    public void remove() {
        decoratedShape.remove();
    }

    @Override
    public void select(MouseEvent e) {
        decoratedShape.select(e);
    }

    @Override
    public void deselect(MouseEvent e) {
        decoratedShape.deselect(e);
    }

    @Override
    public void drag(Location location) {
        decoratedShape.drag(location);
    }

    @Override
    public void resize(Location location) {
        decoratedShape.resize(location);
    }

    @Override
    public void undoDrag() {
        decoratedShape.undoDrag();
    }

    @Override
    public void redoDrag() {
        decoratedShape.redoDrag();
    }

    @Override
    public int getX() {
        return decoratedShape.getX();
    }

    @Override
    public int getY() {
        return decoratedShape.getY();
    }

    @Override
    public int getWidth() {
        return decoratedShape.getWidth();
    }

    @Override
    public int getHeight() {
        return decoratedShape.getHeight();
    }
}
