package abstract_tree;

import visitors.Visitor;

public class ReadInstr extends Instruction{

    Idf id;

    public ReadInstr(Idf id){
        this.id=id;
    }

    public Idf getId() {
        return id;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
