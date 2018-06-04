package abstract_tree;

import visitors.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Block extends Instruction {
    private final ArrayList<Instruction> instructions;


    public Block(Instruction... instructions) {
        this.instructions = new ArrayList<>();
        this.instructions.addAll(Arrays.asList(instructions));
    }

    public void addInstructionBefore(Instruction i){
        this.instructions.add(0, i);
    }
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
