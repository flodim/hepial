package visitors;

import abstract_tree.*;
import symbole_table.TypeBoolean;
import symbole_table.TypeInteger;
import symbole_table.TypeString;

import java.util.HashMap;

public class JasminGenerator extends Visitor {

    private StringBuilder jasminStringBuilder;
    private HashMap<String, Integer> localsIndices;
    private int nextLocalIndex;
    private int nextIfNumber;

    public String generate(Block mainBlock) {
        this.jasminStringBuilder = new StringBuilder();
        this.localsIndices = new HashMap<>();
        this.nextLocalIndex = 0;
        this.nextIfNumber = 0;

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
        loadIfLocalIndex(affectation.getSource().accept(this));

         addLine("istore " + destinationLocalIndex);

        return null;
    }

    @Override
    public Object visit(Addition addition) {
        loadIfLocalIndex(addition.getLeft().accept(this));
        loadIfLocalIndex(addition.getRight().accept(this));

        this.addLine("iadd");

        return null;
    }

    @Override
    public Object visit(Substraction substraction) {
        loadIfLocalIndex(substraction.getLeft().accept(this));
        loadIfLocalIndex(substraction.getRight().accept(this));

        this.addLine("isub");

        return null;
    }

    @Override
    public Object visit(Multiplication multiplication) {
        loadIfLocalIndex(multiplication.getLeft().accept(this));
        loadIfLocalIndex(multiplication.getRight().accept(this));

        this.addLine("imul");

        return null;
    }


    @Override
    public Object visit(Division division) {
        loadIfLocalIndex(division.getLeft().accept(this));
        loadIfLocalIndex(division.getRight().accept(this));

        this.addLine("idiv");

        return null;
    }

    @Override
    public Object visit(Condition condition) {
        loadIfLocalIndex(condition.getConditionExpression().accept(this));

        int ifNumber = this.nextIfNumber++;
        String elseLabel = "else_"+ifNumber;
        String endLabel = "endif_"+ifNumber;

        this.addLine("ifeq "+elseLabel);
        condition.getThenInstructions().accept(this);
        this.addLine("goto "+endLabel);

        this.addLine(elseLabel+":");
        condition.getElseInstructions().accept(this);

        this.addLine(endLabel+":");

        return null;
    }

    @Override
    public Object visit(WhileLoop whileLoop) {
        int ifNumber = this.nextIfNumber++;
        String beginLabel = "while_"+ifNumber;
        String endLabel = "endwhile_"+ifNumber;

        this.addLine(beginLabel+":");
        loadIfLocalIndex(whileLoop.getConditionExpression().accept(this));

        this.addLine("ifeq "+endLabel);
        whileLoop.getInstructions().accept(this);

        this.addLine("goto "+beginLabel);

        this.addLine(endLabel+":");

        return null;
    }

    @Override
    public Object visit(Inferior inferior) {
        loadComparisonResult(inferior, "if_icmplt");
        return null;
    }

    @Override
    public Object visit(InfEqual infEqual) {
        loadComparisonResult(infEqual, "if_icmple");
        return null;
    }

    @Override
    public Object visit(Superior superior) {
        loadComparisonResult(superior, "if_icmpgt");
        return null;
    }

    @Override
    public Object visit(SupEqual supEqual) {
        loadComparisonResult(supEqual, "if_icmpge");
        return null;
    }

    @Override
    public Object visit(Equal equal) {
        loadComparisonResult(equal, "if_icmpeq");
        return null;
    }

    @Override
    public Object visit(NotEqual notEqual) {
        loadComparisonResult(notEqual, "if_icmpne");
        return null;
    }

    @Override
    public Object visit(WriteInstr writeInstr) {
        addLine("getstatic java/lang/System/out Ljava/io/PrintStream;");

        loadIfLocalIndex(writeInstr.getExpr().accept(this));

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

    private void loadIfLocalIndex(Object maybeLocalIndex) {
        if (maybeLocalIndex != null) {
            this.addLine("iload " + (int)maybeLocalIndex);
        }
    }

    private void loadComparisonResult(Comparison comparison, String ifInstruction) {
        loadIfLocalIndex(comparison.getLeft().accept(this));
        loadIfLocalIndex(comparison.getRight().accept(this));

        int ifNumber = nextIfNumber++;
        String thenLabel = "then_"+ifNumber;
        String endifLabel = "endif_"+ifNumber;

        addLine(ifInstruction+" "+thenLabel);
        addLine("ldc 0");
        addLine("goto "+endifLabel);
        addLine(thenLabel+":");
        addLine("ldc 1");
        addLine(endifLabel+":");
    }
}
