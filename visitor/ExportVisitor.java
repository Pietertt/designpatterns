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

      public void visit(Shape shape){
            System.out.println("Shape exported");
      }

      public void visit(Group group){

      }
}