package abstract_tree;

import visitors.Visitor;

public class Condition extends Instruction {
    private final Expression conditionExpression;
    private final Block thenInstructions;
    private final Block elseInstructions;

    public Condition(Expression conditionExpression, Block thenInstructions, Block elseInstructions) {
        this.conditionExpression = conditionExpression;
        this.thenInstructions = thenInstructions;
        this.elseInstructions = elseInstructions;
    }

    public Expression getConditionExpression() {
        return conditionExpression;
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
