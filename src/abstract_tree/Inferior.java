package abstract_tree;

import visitors.Visitor;

public class Inferior extends Comparison {
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
