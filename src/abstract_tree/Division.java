package abstract_tree;

public class Division extends Arithmetic {
    public Division(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "/";
    }
}
