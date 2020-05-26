package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;

import shapes.*;
import shapes.Shape;
import commands.*;

public class ResizeVisitor extends Visitor {
      public BaseShape selectedShape;
      public Group group = null;

      @Override
      public void visit(BaseShape shape) {
            this.selectedShape = shape;
      }

      @Override
      public void visit(Group group) {
            this.selectedShape = group;
            this.group = group;
      }

      @Override
      public void visit(TextShapeDecorator shapeDecorator) {
            this.selectedShape = shapeDecorator.getDecoratedShape();
            this.group = group;
      }

      public void resize(Location location) {
            if(group == null) {

                  selectedShape.x = location.x;
                  selectedShape.y = location.y;
                  selectedShape.width = location.width;
                  selectedShape.height = location.height;
                  selectedShape.repaint();
            } else {
                  boolean s = false;
                  for(BaseShape shape : group.children){
                        if(shape.selected){
                              s = true;
                              Location childLocation = new Location(shape.x, shape.y, location.width - (shape.start.x - location.x), location.height - (shape.start.y - location.y));
                              ResizeVisitor resizeVisitor = new ResizeVisitor();
                              shape.accept(resizeVisitor);
                              resizeVisitor.resize(childLocation);
                        }
                  }

                  if(!s){
                        float percentageWidth = (float)location.width / (float)group.start.width;
                        float percentageHeight = (float)location.height / (float)group.start.height;

                        for(BaseShape shape : group.children){
                              float diffX = ((float)shape.start.x - (float)group.x) * percentageWidth;
                              float diffY = ((float)shape.start.y - (float)group.y) * percentageHeight;

                              Location childLocation = new Location();
                              childLocation.x = group.start.x + Math.round(diffX);
                              childLocation.y = group.start.y + Math.round(diffY);
                              childLocation.width = Math.round((float)shape.start.width * percentageWidth);
                              childLocation.height = Math.round((float)shape.start.height * percentageHeight);
                              ResizeVisitor resizeVisitor = new ResizeVisitor();
                              shape.accept(resizeVisitor);
                              resizeVisitor.resize(childLocation);
                        }
                  }
            }
      }
}