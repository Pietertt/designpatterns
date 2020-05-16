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
    public Board board;
    public JFrame frame;
//    public boolean shifted;

    public moveVisitor(Board board, JFrame frame) {
        this.board = board;
        this.frame = frame;
    }


    @Override
    public void visitRectangle(Rectangle rectangle) {
        System.out.println("In visitRectangle..");
//        rectangle.addMouseListener(new MouseAdapter() {
//
//            public void mouseReleased(MouseEvent e) {
//                rectangle.clear();
//            }
//
//            public void mousePressed(MouseEvent e) {
//                if (rectangle.selected) {
//                    if (rectangle.getIfSelected(e.getX(), e.getY())) {
//                        Order drag = new DragShapeCommand(rectangle,
//                                new Location(rectangle.x, rectangle.y, rectangle.width, rectangle.height));
//                        invoker.execute(drag);
//                    } else {
//                        if (rectangle.getHandleIfSelected(e.getX(), e.getY())) {
//                            Order resize = new ResizeShapeCommand(rectangle,
//                                    new Location(rectangle.x, rectangle.y, rectangle.width, rectangle.height));
//                            invoker.execute(resize);
//
//                        } else {
//                            if (!shifted) {
//                                Order deselect = new DeselectShapeCommand(rectangle, e);
//                                invoker.execute(deselect);
//                            }
//                        }
//                    }
//                }
//            }
//        });

        rectangle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Klikkkk");
            }
        });



//        rectangle.addMouseMotionListener(new MouseAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                System.out.println("Dragged in visitor");
//                if (rectangle.resizing) {
//                    Location location = new Location(rectangle.x, rectangle.y, e.getX() - rectangle.start.x,
//                            e.getY() - rectangle.start.y);
//                    rectangle.move(location);
//                }
//
//                if (rectangle.dragging) {
//                    Location location = new Location(e.getX(), e.getY(), rectangle.width, rectangle.height);
//                    rectangle.move(location);
//                }
//            }
//        });

        this.board.add(rectangle);
        this.frame.add(rectangle);
    }

    @Override
    public void visitEllipse(Ellipse ellipse) {

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