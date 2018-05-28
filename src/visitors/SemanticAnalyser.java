package visitors;

import abstract_tree.*;
import symbole_table.ErrorManager;
import symbole_table.Type;
import symbole_table.TypeInteger;

public class SemanticAnalyser implements Visitor {


    @Override
    public Object visit(Affectation affectation) {
        affectation.getDestination().accept(this);
        Type destinationType = affectation.getDestination().getType();

        affectation.getSource().accept(this);
        Type sourceType = affectation.getSource().getType();

        if (!sourceType.isConform(destinationType)) {
            ErrorManager.getInstance().addError("Type error: source type of affectation is not conform to destination type.");
        }

        return null;
    }

    @Override
    public Object visit(Arithmetic arithmetic) {
        arithmetic.getLeft().accept(this);
        arithmetic.getRight().accept(this);

        Type leftType = arithmetic.getLeft().getType();
        Type rightType = arithmetic.getRight().getType();

        if (!leftType.isConform(TypeInteger.getInstance())) {
            ErrorManager.getInstance().addError("Type error: left side of the arithmetic expression is not integer.");
        }

        if (!rightType.isConform(TypeInteger.getInstance())) {
            ErrorManager.getInstance().addError("Type error: right side of the arithmetic expression is not integer.");
        }

        return null;
    }

    @Override
    public Object visit(Block block) {
        return null;
    }

    @Override
    public Object visit(BooleanValue booleanValue) {
        return null;
    }

    @Override
    public Object visit(Call call) {
        return null;
    }

    @Override
    public Object visit(Condition condition) {
        return null;
    }

    @Override
    public Object visit(Equal equal) {
        return null;
    }

    @Override
    public Object visit(ForLoop forLoop) {
        return null;
    }

    @Override
    public Object visit(Idf idf) {
        return null;
    }

    @Override
    public Object visit(InfEqual infEqual) {
        return null;
    }

    @Override
    public Object visit(Inferior inferior) {
        return null;
    }

    @Override
    public Object visit(IntNumber intNumber) {
        return null;
    }

    @Override
    public Object visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public Object visit(QualifiedCall qualifiedCall) {
        return null;
    }

    @Override
    public Object visit(SupEqual supEqual) {
        return null;
    }

    @Override
    public Object visit(Superior superior) {
        return null;
    }
}
