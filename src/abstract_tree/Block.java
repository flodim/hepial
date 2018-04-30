package abstract_tree;

import java.util.ArrayList;
import java.util.List;

public class Block extends Instruction {
    private final Instruction[] instructions;


    public Block(Instruction... instructions) {
        this.instructions = instructions;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }
}
