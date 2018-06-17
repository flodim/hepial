package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeBoolean;
import visitors.Visitor;

public class Not extends Unary {

    @Override
    public String toString() {
        return "non";
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
    @Override

    public Type getType() {
        return TypeBoolean.getInstance();
    }

}
