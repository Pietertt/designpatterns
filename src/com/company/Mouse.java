package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class Mouse extends MouseAdapter {
    JPanel panel;

    static Boolean isEllipseBtnPressed = false;
    static Boolean isRectBtnPressed = false;

    List<Shape> shapes;

    //actual component that mouse hoovers over
    Shape component;

    boolean dragging = false;
    final int PROXDISTANCE = 3;


    //JComponenten lijst? voor shapes
    Mouse(JPanel panel/*, List<JComponent> jComponentList*/)
    {
        this.panel = panel;
        //this.shapes.addAll(jComponentList);
    }

    public void switchEllipse() {
        isRectBtnPressed = false;
        isEllipseBtnPressed =! isEllipseBtnPressed;
    }

    public void switchRectangle() {
        isEllipseBtnPressed = false;
        isRectBtnPressed =! isRectBtnPressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if(component.getCursor() != Cursor.getDefaultCursor()) {
//            // If cursor is set for resizing, allow dragging.
//            dragging = true;
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;

        int x = e.getX();
        int y = e.getY();

        System.out.println("(x, y) of mouse click= (" + x + ", " + y + ")");
        if(isRectBtnPressed) {
            Shape rect = new Rectangle(x, y);
            panel.add(rect);
        }
        if(isEllipseBtnPressed) {
            Shape el = new Ellipse(x,y);
            panel.add(el);
        }

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            Point p = e.getPoint();
            java.awt.Rectangle rect = new java.awt.Rectangle(component.x, component.y, 100, 100);
            int type = component.getCursor().getType();
            int dx = p.x - rect.x;
            int dy = p.y - rect.y;
            switch(type) {
                case Cursor.N_RESIZE_CURSOR:
                    int height = rect.height - dy;
                    rect.setRect(rect.x, rect.y+dy, rect.width, rect.height);
                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    int width = rect.width - dx;
                    height = rect.width - dy;
                    rect.setRect(rect.x+dx, rect.y+dy,width,height);
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    width = rect.width - dx;
                    rect.setRect(rect.x+dx, rect.y, width, rect.height);
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    width = rect.width - dx;
                    height = dy;
                    rect.setRect(rect.x+dx, rect.y, width, height);
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    height = dy;
                    rect.setRect(rect.x, rect.y, rect.width, height);
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    width = dx;
                    height = dy;
                    rect.setRect(rect.x, rect.y, width, height);
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    width = dx;
                    rect.setRect(rect.x, rect.y, width, rect.height);
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    width = dx;
                    height = rect.height - dy;
                    rect.setRect(rect.x,rect.y+dy, width, height);
                    break;
                default:
                    System.out.println("Unexpected: " + type);
            }
            component.repaint();
        }
    }

    private int getOutcode(Point p) {
        Shape r = component.copy();
        //r.x = r.x - PROXDISTANCE;
        //r.y = r.y - PROXDISTANCE;

        java.awt.Rectangle rect = new java.awt.Rectangle(r.x, r.y, 100 - PROXDISTANCE, 100 - PROXDISTANCE);
        return rect.outcode(p.x, p.y);
    }

    boolean isOverComponent(Point p) {
        for(int i = 0; i < shapes.size(); i++) {
            Shape r = shapes.get(i).copy();
            r.x = 100 + PROXDISTANCE;
            r.y = 100 + PROXDISTANCE;
            if(shapes.get(i).contains(p)) {
                this.component = shapes.get(i);
                return true;
            }
        }
        return false;
    }
}
