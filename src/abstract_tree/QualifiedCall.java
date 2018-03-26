package abstract_tree;

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
}
