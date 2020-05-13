package visitor;

import shapes.*;

public interface Visitor {
       void visitRectangle(Rectangle rectangle);
       void visitEllipse(Ellipse ellipse);
       void visit(BaseShape shape);
}