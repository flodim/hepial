package abstract_tree;

import visitors.Visitor;

import java.util.List;

public class Condition extends Instruction {
    private final Expression condition;
    private final Block thenInstructions;
    private final Block elseInstructions;

    public Condition(Expression condition, Block thenInstructions, Block elseInstructions) {
        this.condition = condition;
        this.thenInstructions = thenInstructions;
        this.elseInstructions = elseInstructions;
    }

    public Expression getCondition() {
        return condition;
    }

    public Block getThenInstructions() {
        return thenInstructions;
    }

    public Block getElseInstructions() {
        return elseInstructions;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
