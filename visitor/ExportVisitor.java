package visitor;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;
import java.awt.event.*;

import IO.GetGroup;
import shapes.*;
import shapes.Shape;
import commands.*;

public class ExportVisitor extends Visitor {
      private StringBuilder sb = new StringBuilder();

      public void visit(BaseShape shape){
            sb.append(shape.toString(0) + " " + shape.x + " " + shape.y + " " + shape.width + " " + shape.height + "");
      }

      public void visit(Group group){
            sb.append(group.toString(0));
      }

      @Override
      public void visit(TextShapeDecorator shapeDecorator) {

      }


      public String export(){
            return this.sb.toString();
      }     
}