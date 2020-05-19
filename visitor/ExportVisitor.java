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
            sb.append(shape.toString(0) + " " + shape.x + " " + shape.y + " " + shape.width + " " + shape.height + "\n");
      }

      public void visit(Group group){
            sb.append(group.toString(0));
      }

      public String export(){
            return this.sb.toString();
      }     
}