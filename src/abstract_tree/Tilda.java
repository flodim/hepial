package abstract_tree;


import visitors.Visitor;

public class Tilda extends  Unary{


    @Override
    public String toString() {
        return "~";
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
