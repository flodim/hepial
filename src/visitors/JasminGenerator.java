package visitors;

import abstract_tree.*;
import symbole_table.Type;
import symbole_table.TypeBoolean;
import symbole_table.TypeInteger;
import symbole_table.TypeString;

public class JasminGenerator extends Visitor {

    private StringBuilder jasminStringBuilder;

    public String generate(Block mainBlock) {
        this.jasminStringBuilder = new StringBuilder();

        addLine(".class public HepialProgram");
        addLine(".super java/lang/Object");

        // constructor
        addLine(".method public <init>()V");
        addLine("aload_0");
        addLine("invokespecial java/lang/Object/<init>()V");
        addLine("return");
        addLine(".end method");

        // main
        addLine(".method public static main([Ljava/lang/String;)V");

        addLine(".limit stack 100");

        addLine("getstatic java/lang/System/out Ljava/io/PrintStream;");


        this.visit(mainBlock);

        addLine("return");
        addLine(".end method");

        return this.jasminStringBuilder.toString();
    }

    @Override
    public Object visit(Block block) {

        for (Instruction instruction : block.getInstructions()) {
            instruction.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(IntNumber intNumber) {
        this.addLine("ldc "+intNumber.getValue());
        return null;
    }

    @Override
    public Object visit(BooleanValue booleanValue) {
        int intValue = booleanValue.getValue()? 1 : 0;
        this.addLine("ldc " + intValue);
        return null;
    }

    @Override
    public Object visit(StringValue stringValue) {
        this.addLine("ldc " + stringValue.getValue());
        return null;
    }

    @Override
    public Object visit(Affectation affectation) {
        affectation.getDestination().accept(this);
        affectation.getSource().accept(this);

        return null;
    }

    @Override
    public Object visit(Addition addition) {
        addition.getLeft().accept(this);
        addition.getRight().accept(this);

        this.addLine("iadd");

        return null;
    }

    @Override
    public Object visit(WriteInstr writeInstr) {
        writeInstr.getExpr().accept(this);

        if (writeInstr.getExpr().getType().isConform(TypeInteger.getInstance()) ||
                writeInstr.getExpr().getType().isConform(TypeBoolean.getInstance())) {

            this.addLine("invokevirtual java/io/PrintStream/println(I)V");
        }
        else if (writeInstr.getExpr().getType().isConform(TypeString.getInstance())) {
            this.addLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        }


        return null;
    }

    private void addLine(String line) {
        jasminStringBuilder.append(line);
        jasminStringBuilder.append('\n');
    }
}
