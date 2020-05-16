package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;

import UI.Board;
import UI.Invoker;
import shapes.*;
import shapes.Rectangle;
import shapes.Shape;
import commands.*;

public class moveVisitor implements Visitor {
//    public Invoker invoker;
    private Board board;
    private JFrame frame;
    private Location location;
//    public boolean shifted;

    public moveVisitor(Board board, JFrame frame) {
        this.board = board;
        this.frame = frame;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public void visitRectangle(Rectangle rectangle) {
        System.out.println("In visitRectangle..");


        // Drag() method
        rectangle.x = location.x;
        rectangle.y = location.y;
        rectangle.width = location.width;
        rectangle.height = location.height;
        rectangle.repaint();

//        MouseAdapter mouseAdapter = new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("Klikkkk");
//            }
//        };
//
//        rectangle.addMouseListener(mouseAdapter);
    }

    @Override
    public void visitEllipse(Ellipse ellipse) {
        // Drag() method
        ellipse.x = location.x;
        ellipse.y = location.y;
        ellipse.width = location.width;
        ellipse.height = location.height;
        ellipse.repaint();
    }

    @Override
    public void visitGroup(Group shapes) {
        System.out.println("In visitGroup");
        boolean s = false;
        for(BaseShape shape : shapes.children) {
            if (shape.selected) {
                Location childLocation = new Location(location.x, location.y, shape.width, shape.height);
                //shape.drag(childLocation);
                this.setLocation(childLocation);
                shape.accept(this);
                //moveVisitor.setLocation(childLocation);
                //shape.accept(moveVisitor);
                System.out.println(shapes.handle);

                s = true;

            }
        }

        if(s == false) {
            for (BaseShape shape : shapes.children) {
                int dx = location.x - shape.start.x + shape.start.x;
                int dy = location.y - shape.start.y + shape.start.y;

                Location childLocation = new Location();
                childLocation.x = dx;
                childLocation.y = dy;
                childLocation.width = shape.width;
                childLocation.height = shape.height;

                this.setLocation(childLocation);

                shape.accept(this);
                //moveVisitor.setLocation(childLocation);
                //shape.accept(moveVisitor);

            }
            shapes.repaint();
        }
    }

    @Override
    public void visit(BaseShape shape) {
//        System.out.println("Dragged in visitor");
//        shape.addMouseMotionListener(new MouseAdapter() {
//            public void mouseDragged(MouseEvent e) {
//
//                if (shape.resizing) {
//                    Location location = new Location(shape.x, shape.y, e.getX() - shape.start.x,
//                            e.getY() - shape.start.y);
//                    shape.move(location);
//                }
//
//                if (shape.dragging) {
//                    Location location = new Location(e.getX(), e.getY(), shape.width, shape.height);
//                    shape.move(location);
//                }
//            }
//        });
    }
}