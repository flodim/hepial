package abstract_tree;

import visitors.Visitor;

public class Superior extends Comparison {

    @Override
    public String Operator() {
        return ">";
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
