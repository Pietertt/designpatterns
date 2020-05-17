package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;

import UI.Board;
import UI.Invoker;
import shapes.*;
import shapes.Rectangle;
import shapes.Shape;
import commands.*;

public class moveVisitor implements Visitor {
    public BaseShape selectedShape;
    public Group group = null;

    @Override
    public void visit(BaseShape shape) {
        this.selectedShape = shape;
    }

    @Override
    public void visit(Group group) {
        System.out.println("Visited group");
        this.selectedShape = group;
        this.group = group;
    }

    public void drag(Location location) {
        if (group == null) {
            selectedShape.x = location.x;
            selectedShape.y = location.y;
            selectedShape.width = location.width;
            selectedShape.height = location.height;
            selectedShape.repaint();
        } else {
            boolean s = false;
            for (BaseShape shape : group.children) {
                if (shape.selected) {
                    Location childLocation = new Location(location.x, location.y, shape.width, shape.height);
                    moveVisitor moveVisitor = new moveVisitor();
                    shape.accept(moveVisitor);
                    moveVisitor.drag(childLocation);
                    s = true;
                }
            }
            if (s == false) {
                for (BaseShape shape : group.children) {
                    int dx = location.x - group.start.x + shape.start.x;
                    int dy = location.y - group.start.y + shape.start.y;

                    Location childLocation = new Location();
                    childLocation.x = dx;
                    childLocation.y = dy;
                    childLocation.width = shape.width;
                    childLocation.height = shape.height;

                    moveVisitor moveVisitor = new moveVisitor();
                    shape.accept(moveVisitor);
                    moveVisitor.drag(childLocation);
                }
                selectedShape.repaint();
            }
        }
    }
}