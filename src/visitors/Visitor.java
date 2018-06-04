package visitors;

import abstract_tree.*;

public abstract class Visitor {
    public Object visit(Arithmetic arithmetic) {
        return null;
    }

    public Object visit(Affectation affectation) {
        return null;
    }

    public Object visit(Comparison comparison) {
        return null;
    }

    public Object visit(Block block) {
        return null;
    }

    public Object visit(BooleanValue booleanValue) {
        return null;
    }

    public Object visit(Call call) {
        return null;
    }

    public Object visit(Condition condition) {
        return null;
    }

    public Object visit(ForLoop forLoop) {
        return null;
    }

    public Object visit(QualifiedCall qualifiedCall) {
        return null;
    }
}
