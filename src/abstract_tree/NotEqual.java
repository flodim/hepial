package abstract_tree;

import visitors.Visitor;

public class NotEqual extends Comparison {
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
