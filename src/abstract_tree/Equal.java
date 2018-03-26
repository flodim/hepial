package abstract_tree;

public class Equal extends Relation {
    public Equal(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "==";
    }
}
