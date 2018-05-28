package abstract_tree;

import visitors.Visitor;

public class Addition extends Arithmetic {

    @Override
    public String Operator() {
        return "+";
    }
}
