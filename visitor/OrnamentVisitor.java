package visitor;

import shapes.BaseShape;
import shapes.Group;
import shapes.TextShapeDecorator;

public class OrnamentVisitor extends Visitor {
    @Override
    public void visit(BaseShape shape) {

    }

    @Override
    public void visit(Group group) {

    }

    @Override
    public void visit(TextShapeDecorator shapeDecorator) {

    }
}
