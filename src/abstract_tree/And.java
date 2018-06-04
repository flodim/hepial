package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeBoolean;
import visitors.Visitor;

public class And extends Binary {


    @Override
    public String Operator() {
        return "et";
    }

    @Override
    public Type getType() {
        return TypeBoolean.getInstance();
    }

    @Override
    public Object accept(Visitor visitor) {
        return null;
    }
}
