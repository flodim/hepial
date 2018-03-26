package abstract_tree;

public class SupEqual extends Relation{
    public SupEqual(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return ">=";
    }
}
