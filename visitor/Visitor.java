package visitor;

import shapes.*;
import java.awt.event.*;

public abstract class Visitor {
      public abstract void visit(BaseShape shape);
      public abstract void visit(Group group);
}