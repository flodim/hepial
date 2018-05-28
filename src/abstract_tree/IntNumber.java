package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeInteger;
import visitors.Visitor;

public class IntNumber extends Expression{
    private Integer value;

    public IntNumber(Integer val){
        this.value=val;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return TypeInteger.getInstance();
    }
}
