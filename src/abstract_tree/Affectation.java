package abstract_tree;

public class Affectation extends Instruction {
    private final Expression source;
    private final Expression destination;

    public Affectation(Expression source, Expression destination) {
        this.source = source;
        this.destination = destination;
    }

    public Expression getSource() {
        return source;
    }

    public Expression getDestination() {
        return destination;
    }
}
