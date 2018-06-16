package abstract_tree;

import visitors.Visitor;

public class Division extends Arithmetic {
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
