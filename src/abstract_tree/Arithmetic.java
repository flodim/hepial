package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeInteger;
import visitors.Visitor;

public abstract class Arithmetic extends Binary {

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return TypeInteger.getInstance();
    }
}
