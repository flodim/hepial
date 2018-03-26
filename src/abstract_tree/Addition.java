package abstract_tree;

public class Addition extends Arithmetic {
    public Addition(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "+";
    }
}
