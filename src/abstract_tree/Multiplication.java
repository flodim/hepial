package abstract_tree;

import visitors.Visitor;

public class Multiplication extends Arithmetic {
    @Override
    public String Operator() {
        return "*";
    }
}
