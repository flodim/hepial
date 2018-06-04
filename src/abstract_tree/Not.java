package abstract_tree;

import visitors.Visitor;

public class Not extends Unary {

    @Override
    public String toString() {
        return "non";
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
