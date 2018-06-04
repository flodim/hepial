package abstract_tree;
import visitors.Visitor;


public class WhileLoop extends Instruction {

    private final Expression exp;
    private final Block instructions;

    public WhileLoop(Expression exp, Block instructions){
        this.exp=exp;
        this.instructions=instructions;
    }

    public Expression getExp() {
        return exp;
    }

    public Block getInstructions() {
        return instructions;
    }

    @Override
    public Object accept(Visitor visitor) {
        return null;
    }
}
