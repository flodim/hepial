package abstract_tree;

import visitors.Visitor;

import java.util.List;

public class ForLoop extends Instruction {
    private final Idf var;
    private final Expression lowerBoundary;
    private final Expression upperBoundary;
    private final Block instructions;

    public ForLoop(Idf var,Expression lowerBoundary, Expression upperBoundary, Block instructions) {
        this.var=var;
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

    public Idf getVar() {
        return var;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
