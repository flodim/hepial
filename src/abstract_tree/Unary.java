package abstract_tree;

import symbole_table.Type;

public abstract class Unary extends Expression{
    protected Expression exp;

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }


}
