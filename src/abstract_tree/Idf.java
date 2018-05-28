package abstract_tree;

import visitors.Visitor;

public class Idf extends Expression {
    private String name;

    public Idf(String n){
        name=n;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
