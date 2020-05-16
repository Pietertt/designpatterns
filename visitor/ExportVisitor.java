package visitor;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;
import java.awt.event.*;

import shapes.*;
import shapes.Shape;
import commands.*;

public class ExportVisitor extends Visitor {
      private StringBuilder sb = new StringBuilder();

      public void visit(Shape shape){
            sb.append(shape.strategy.toString() + " " + shape.x + " " + shape.y + " " + shape.width + " " + shape.height + "\n");
      }

      public void visit(Group group){
            sb.append(group.strategy.toString() + " " + group.children.size());
      }

      public String export(){
            return this.sb.toString();
      }     
}