package visitors;

import abstract_tree.*;

public interface Visitor {
    public Object visit(Arithmetic arithmetic);
    public Object visit(Affectation affectation);
    public Object visit(Block block);
    public Object visit(BooleanValue booleanValue);
    public Object visit(Call call);
    public Object visit(Condition condition);
    public Object visit(Equal equal);
    public Object visit(ForLoop forLoop);
    public Object visit(Idf idf);
    public Object visit(InfEqual infEqual);
    public Object visit(Inferior inferior);
    public Object visit(IntNumber intNumber);
    public Object visit(NotEqual notEqual);
    public Object visit(QualifiedCall qualifiedCall);
    public Object visit(SupEqual supEqual);
    public Object visit(Superior superior);
}
