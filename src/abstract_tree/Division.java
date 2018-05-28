package abstract_tree;

import visitors.Visitor;

public class Division extends Arithmetic {

    @Override
    public String Operator() {
        return "/";
    }
}
