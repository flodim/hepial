package abstract_tree;

import java.util.List;

public class Call extends Instruction {
    private final String identifier;
    private final List<Expression> parameters;

    public Call(String identifier, List<Expression> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Expression> getParameters() {
        return parameters;
    }
}
