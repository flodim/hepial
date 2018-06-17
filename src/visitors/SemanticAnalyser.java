package visitors;

import abstract_tree.*;
import symbole_table.ErrorManager;
import symbole_table.Type;
import symbole_table.TypeBoolean;
import symbole_table.TypeInteger;

public class SemanticAnalyser extends Visitor {

    @Override
    public Object visit(Block block) {
        for (Instruction instruction : block.getInstructions()) {
            instruction.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(Affectation affectation) {
        affectation.getDestination().accept(this);
        affectation.getSource().accept(this);

        Idf dest=(Idf)affectation.getDestination();
        if(dest.getIsConst()){
            ErrorManager.getInstance().addError("Affection error: destination is a constant.");
        }

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
    public Object visit(Addition addition) {
        this.visit((Arithmetic)addition);
        return null;
    }

    @Override
    public Object visit(Substraction substraction) {
        this.visit((Arithmetic)substraction);
        return null;
    }

    @Override
    public Object visit(Multiplication multiplication) {
        this.visit((Arithmetic)multiplication);
        return null;
    }

    @Override
    public Object visit(Division division) {
        this.visit((Arithmetic) division);

        if (division.getRight() instanceof IntNumber &&
            ((IntNumber) division.getRight()).getValue() == 0) {
            ErrorManager.getInstance().addError("Division by zero error");
        }

        return null;
    }

    @Override
    public Object visit(Not not) {
        not.getExp().accept(this);
        Type exp_type = not.getType();
        if (!exp_type.isConform(TypeBoolean.getInstance())){
            ErrorManager.getInstance().addError("Type error: expression type must be Boolean.");
        }
        return null;
    }

    @Override
    public Object visit(Tilda tilda) {
        tilda.getExp().accept(this);
        Type exp_type = tilda.getType();
        if (!exp_type.isConform(TypeInteger.getInstance())){
            ErrorManager.getInstance().addError("Type error: expression type must be Integer.");
        }
        return null;
    }

    private Object visit(Arithmetic arithmetic) {
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
    public Object visit(Condition condition) {
        condition.getConditionExpression().accept(this);
        condition.getThenInstructions().accept(this);

        if (condition.getElseInstructions() != null) {
            condition.getElseInstructions().accept(this);
        }

        if (!condition.getConditionExpression().getType().isConform(TypeBoolean.getInstance())) {
            ErrorManager.getInstance().addError("Type error: condition is not boolean");
        }

        return null;
    }

    @Override
    public Object visit(ForLoop forLoop) {
        forLoop.getLowerBoundary().accept(this);
        forLoop.getUpperBoundary().accept(this);
        forLoop.getInstructions().accept(this);

        if (!forLoop.getLowerBoundary().getType().isConform(TypeInteger.getInstance())) {
            ErrorManager.getInstance().addError("Type error: for loop lower boundary is not integer");
        }

        if (!forLoop.getUpperBoundary().getType().isConform(TypeInteger.getInstance())) {
            ErrorManager.getInstance().addError("Type error: for loop upper boundary is not integer");
        }

        return null;
    }
}
