package abstract_tree;

import visitors.Visitor;

import java.util.List;

public class ForLoop extends Instruction {
    private final Expression lowerBoundary;
    private final Expression upperBoundary;
    private final Block instructions;

    public ForLoop(String identifier, Expression lowerBoundary, Expression upperBoundary, Block instructions) {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.instructions = instructions;
    }

    public Expression getLowerBoundary() {
        return lowerBoundary;
    }

    public Expression getUpperBoundary() {
        return upperBoundary;
    }

    public Block getInstructions() {
        return instructions;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
