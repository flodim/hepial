package abstract_tree;

import visitors.Visitor;

public class Equal extends Comparison {
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
