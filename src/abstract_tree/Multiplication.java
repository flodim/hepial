package abstract_tree;

public class Multiplication extends Arithmetic {
    public Multiplication(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "*";
    }
}
