package abstract_tree;

public class Inferior extends Relation {
    public Inferior(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public String Operator() {
        return "<";
    }
}
