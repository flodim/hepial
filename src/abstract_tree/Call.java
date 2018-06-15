package abstract_tree;

import visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Call extends Instruction {
    private final Idf identifier;
    private final List<Expression> parameters;

    public Call(Idf identifier, List<Expression> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public Call(Idf identifier){
        this(identifier,new ArrayList<Expression>());
    }

    public void addParameterBefore(Expression e){
        this.parameters.add(0, e);
    }

    public Idf getIdentifier() {
        return identifier;
    }

    public List<Expression> getParameters() {
        return parameters;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
