package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeBoolean;
import visitors.Visitor;

public class Or extends Binary {
    @Override
    public Type getType() {
        return TypeBoolean.getInstance();
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
