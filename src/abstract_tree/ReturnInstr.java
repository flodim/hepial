package abstract_tree;

import visitors.Visitor;

public class ReturnInstr extends Instruction {
    Expression exp;

    public ReturnInstr(Expression exp){
        this.exp=exp;
    }

    public Expression getExp() {
        return exp;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
