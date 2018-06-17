package visitors;

import abstract_tree.*;
import symbole_table.Type;
import symbole_table.TypeBoolean;
import symbole_table.TypeInteger;
import symbole_table.TypeString;

import java.util.HashMap;

public class JasminGenerator extends Visitor {

    private StringBuilder jasminStringBuilder;
    private HashMap<String, Integer> localsIndices;
    private int nextLocalIndex;

    public String generate(Block mainBlock) {
        this.jasminStringBuilder = new StringBuilder();
        this.localsIndices = new HashMap<>();
        this.nextLocalIndex = 0;

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
    public Object visit(Idf idf) {
        Integer localIndex = this.localsIndices.get(idf.getName());
        if (localIndex == null) {
            localIndex = nextLocalIndex++;
            this.localsIndices.put(idf.getName(), localIndex);
        }

        return localIndex;
    }

    @Override
    public Object visit(Affectation affectation) {
        int destinationLocalIndex = (Integer) affectation.getDestination().accept(this);
        Integer sourceLocalIndex = (Integer)affectation.getSource().accept(this);

        if (sourceLocalIndex != null) {
            // the source is an IDF
            addLine("iload " + sourceLocalIndex);
        }

        addLine("istore " + destinationLocalIndex);

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
    public Object visit(Substraction substraction) {
        substraction.getLeft().accept(this);
        substraction.getRight().accept(this);

        this.addLine("isub");

        return null;
    }

    @Override
    public Object visit(Multiplication multiplication) {
        multiplication.getLeft().accept(this);
        multiplication.getRight().accept(this);

        this.addLine("imul");

        return null;
    }


    @Override
    public Object visit(Division division) {
        division.getLeft().accept(this);
        division.getRight().accept(this);

        this.addLine("idiv");

        return null;
    }

    @Override
    public Object visit(Not not){
        not.getExp().accept(this);
        return null;

    }

    @Override
    public Object visit(Tilda tilda) {
        tilda.getExp().accept(this);
        return null;
    }

    @Override
    public Object visit(Comparison comparison) {
        return super.visit(comparison);
    }

    @Override
    public Object visit(WriteInstr writeInstr) {
        addLine("getstatic java/lang/System/out Ljava/io/PrintStream;");

        Integer exprLocalIndex = (Integer) writeInstr.getExpr().accept(this);
        if (exprLocalIndex != null) {
            this.addLine("iload " + exprLocalIndex);
        }

        if (writeInstr.getExpr().getType().isConform(TypeInteger.getInstance()) ||
                writeInstr.getExpr().getType().isConform(TypeBoolean.getInstance())) {

            this.addLine("invokevirtual java/io/PrintStream/println(I)V");
        }
        else if (writeInstr.getExpr().getType().isConform(TypeString.getInstance())) {
            this.addLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        }


        return null;
    }

    @Override
    public Object visit(ReadInstr readInstr) {

        addLine("new java/util/Scanner");
        addLine("dup");
        addLine("getstatic java/lang/System/in Ljava/io/InputStream;");
        addLine("invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V");
        addLine("invokevirtual java/util/Scanner/nextInt()I");

        int destinationLocalIndex = (Integer)readInstr.getId().accept(this);
        addLine("istore "+ destinationLocalIndex);

        return null;
    }

    private void addLine(String line) {
        jasminStringBuilder.append(line);
        jasminStringBuilder.append('\n');
    }
}
