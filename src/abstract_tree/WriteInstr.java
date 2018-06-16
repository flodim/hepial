package abstract_tree;

import visitors.Visitor;

public class WriteInstr extends Instruction {
    Expression expr;

    public WriteInstr(Expression e){
        this.expr=e;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
