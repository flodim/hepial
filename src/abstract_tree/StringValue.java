package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeString;
import visitors.Visitor;

public class StringValue extends Expression {
    private String value;

    public StringValue(String val){
        this.value=val;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return TypeString.getInstance();
    }
}
