package abstract_tree;

public class InfEqual extends Relation{
    public InfEqual(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "<=";
    }
}
