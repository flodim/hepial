package abstract_tree;

public class Substraction extends Arithmetic {
    public Substraction(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "-";
    }
}
