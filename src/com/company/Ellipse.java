package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {
    int x,y;

    Ellipse(int x, int y) {
        super(x,y);
        this.x = x;
        this.y = y;
        setLocation(x, y);
        setSize( getPreferredSize() );
    }

    Ellipse(Ellipse ellipse) {
        super(ellipse.x, ellipse.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        g2.fill(new Ellipse2D.Double(x, y, 100, 100));
    }

    @Override
    public Shape copy() {
        return new Ellipse(this);
    }

//    @Override
//    public Dimension getPreferredSize()
//    {
//        return new Dimension(100, 100);
//    }
}
