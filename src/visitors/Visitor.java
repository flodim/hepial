package visitors;

import abstract_tree.*;

public interface Visitor {
    public Object visit(Arithmetic arithmetic);
    public Object visit(Affectation affectation);
    public Object visit(Comparison comparison);
    public Object visit(Block block);
    public Object visit(BooleanValue booleanValue);
    public Object visit(Call call);
    public Object visit(Condition condition);
    public Object visit(ForLoop forLoop);
    public Object visit(Idf idf);
    public Object visit(QualifiedCall qualifiedCall);
}
