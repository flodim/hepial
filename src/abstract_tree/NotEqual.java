package abstract_tree;

public class NotEqual extends Relation{

    public NotEqual(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "!=";
    }
}
