package abstract_tree;

import java.util.List;

public class Condition extends Instruction {
    private final Expression condition;
    private final List<Instruction> thenInstructions;
    private final List<Instruction> elseInstructions;

    public Condition(Expression condition, List<Instruction> thenInstructions, List<Instruction> elseInstructions) {
        this.condition = condition;
        this.thenInstructions = thenInstructions;
        this.elseInstructions = elseInstructions;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Instruction> getThenInstructions() {
        return thenInstructions;
    }

    public List<Instruction> getElseInstructions() {
        return elseInstructions;
    }
}
