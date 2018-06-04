package abstract_tree;

import symbole_table.Entry;
import symbole_table.SymbTable;
import symbole_table.Type;
import visitors.Visitor;

public class Idf extends Expression {
    private String name;

    public Idf(String n){
        name=n;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return SymbTable.getInstance().identifier(new Entry(this)).getType();
    }
}
