package abstract_tree;

import visitors.Visitor;

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

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
