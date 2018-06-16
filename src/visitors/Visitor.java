package visitors;

import abstract_tree.*;

import javax.swing.text.html.ObjectView;

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

    public Object visit(StringValue stringValue){ return  null; }

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

    public Object visit(Tilda tilda) {
        return null;
    }

    public Object visit(Not not) {
        return null;
    }

    public Object visit(Idf idf){return null; }

    public Object visit(IntNumber intNumber){return null;}

    public Object visit(ReturnInstr instr){return null;}

    public Object visit(WriteInstr instr){return null;}

    public Object visit(ReadInstr instr){return null;}
}
