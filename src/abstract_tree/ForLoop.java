package abstract_tree;

import visitors.Visitor;

import java.util.List;

public class ForLoop extends Instruction {
    private final String identifier;
    private final Expression lowerBoundary;
    private final Expression upperBoundary;
    private final List<Instruction> instructions;

    public ForLoop(String identifier, Expression lowerBoundary, Expression upperBoundary, List<Instruction> instructions) {
        this.identifier = identifier;
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.instructions = instructions;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getLowerBoundary() {
        return lowerBoundary;
    }

    public Expression getUpperBoundary() {
        return upperBoundary;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
