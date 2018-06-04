package visitors;

import abstract_tree.*;
import symbole_table.ErrorManager;
import symbole_table.Type;
import symbole_table.TypeInteger;

public class SemanticAnalyser implements Visitor {

    @Override
    public Object visit(Affectation affectation) {
        affectation.getDestination().accept(this);
        affectation.getSource().accept(this);

        Type destinationType = affectation.getDestination().getType();
        Type sourceType = affectation.getSource().getType();

        if (!sourceType.isConform(destinationType)) {
            ErrorManager.getInstance().addError("Type error: source type of affectation is not conform to destination type.");
        }

        return null;
    }

    @Override
    public Object visit(Comparison comparison) {
        comparison.getLeft().accept(this);
        comparison.getRight().accept(this);

        Type leftType = comparison.getLeft().getType();
        Type rightType = comparison.getRight().getType();

        if (!rightType.isConform(leftType)) {
            ErrorManager.getInstance().addError("Type error: left type of comparison is not conform to right type.");
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
    public Object visit(ForLoop forLoop) {
        return null;
    }

    @Override
    public Object visit(Idf idf) {
        return null;
    }

    @Override
    public Object visit(QualifiedCall qualifiedCall) {
        return null;
    }
}
