package visitor;

import shapes.*;

public interface Visitor {
       void visit(BaseShape shape);
       void visit(Group group);
}