package abstract_tree;
import visitors.Visitor;


public class WhileLoop extends Instruction {

    private final Expression conditionExpression;
    private final Block instructions;

    public WhileLoop(Expression exp, Block instructions){
        this.conditionExpression =exp;
        this.instructions=instructions;
    }

    public Expression getConditionExpression() {
        return conditionExpression;
    }

    public Block getInstructions() {
        return instructions;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
