package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Shape extends JComponent {
    int x,y;

    Shape(int x, int y) {
        this.x = x;
        this.y = y;
        setLocation(x, y);
        setSize( getPreferredSize() );
    }

    Shape(Shape shape) {
        this.x = shape.x;
        this.y = shape.y;
    }

    protected void paintComponent(Graphics g) {

    }

    public Dimension getPreferredSize()
    {
        return new Dimension(100, 100);
    }

    public Shape copy() {
        return new Shape(this);
    }
}
