package abstract_tree;

public class Superior extends Relation {


    public Superior(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return ">";
    }
}
