package abstract_tree;

import visitors.Visitor;

public class Affectation extends Instruction {
    private final Expression source;
    private final Idf destination;

    public Affectation(Expression source, Idf destination) {
        this.source = source;
        this.destination = destination;
    }

    public Expression getSource() {
        return source;
    }

    public Idf getDestination() {
        return destination;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
