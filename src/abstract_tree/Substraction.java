package abstract_tree;

import visitors.Visitor;

public class Substraction extends Arithmetic {
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
