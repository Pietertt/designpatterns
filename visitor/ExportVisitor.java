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
            sb.append(shape.strategy.grammar(shape));
      }

      public void visit(Group group){
            sb.append(group.strategy.grammar(group));
      }

      public String export(){
            return this.sb.toString();
      }     
}