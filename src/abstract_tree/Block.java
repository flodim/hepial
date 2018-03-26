package abstract_tree;

import java.util.List;

public class Block extends Instruction {
    private final List<Instruction> instructions;

    public Block(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
