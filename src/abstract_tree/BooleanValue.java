package abstract_tree;


import symbole_table.Type;
import symbole_table.TypeBoolean;
import symbole_table.TypeInteger;
import visitors.Visitor;

public class BooleanValue extends Expression{
    private boolean value;

    public BooleanValue(boolean v){
        this.value=v;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value?"vrai":"faux";
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
