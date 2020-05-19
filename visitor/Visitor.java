package visitor;

import shapes.*;
import java.awt.event.*;

public abstract class Visitor {
      public abstract void visit(Shape shape);
      public abstract void visit(Group group);
      public abstract void visit(TextShapeDecorator decorator);
}