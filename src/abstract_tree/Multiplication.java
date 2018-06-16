package abstract_tree;

import visitors.Visitor;

public class Multiplication extends Arithmetic {
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
