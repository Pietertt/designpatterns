package com.company;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends Shape {
    int x,y;

    Rectangle(int x, int y) {
        super(x,y);
        this.x = x;
        this.y = y;
        setLocation(x, y);
        setSize( getPreferredSize() );
    }

    Rectangle(Rectangle rectangle) {
        super(rectangle.x, rectangle.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here

        g.fillRect(x, y, 100, 100);
    }

    @Override
    public Shape copy() {
        return new Rectangle(this);
    }

//    @Override
//    public Dimension getPreferredSize()
//    {
//        return new Dimension(100, 100);
//    }
}
