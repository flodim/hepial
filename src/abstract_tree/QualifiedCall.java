package abstract_tree;

import visitors.Visitor;

import java.util.List;

public class QualifiedCall extends Call {

    private final Expression receiver;

    public QualifiedCall(String identifier, List<Expression> parameters, Expression receiver) {
        super(identifier, parameters);
        this.receiver = receiver;
    }

    public Expression getReceiver() {
        return receiver;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
