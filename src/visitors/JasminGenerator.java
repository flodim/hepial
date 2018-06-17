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
        addLine(".limit locals 100");

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
        addLine("ldc "+intNumber.getValue());
        return null;
    }

    @Override
    public Object visit(BooleanValue booleanValue) {
        int intValue = booleanValue.getValue()? 1 : 0;
        addLine("ldc " + intValue);
        return null;
    }

    @Override
    public Object visit(StringValue stringValue) {
        addLine("ldc " + stringValue.getValue());
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

        addLine("iadd");

        return null;
    }

    @Override
    public Object visit(Substraction substraction) {
        loadIfLocalIndex(substraction.getLeft().accept(this));
        loadIfLocalIndex(substraction.getRight().accept(this));

        addLine("isub");

        return null;
    }

    @Override
    public Object visit(Multiplication multiplication) {
        loadIfLocalIndex(multiplication.getLeft().accept(this));
        loadIfLocalIndex(multiplication.getRight().accept(this));

        addLine("imul");

        return null;
    }


    @Override
    public Object visit(Division division) {
        loadIfLocalIndex(division.getLeft().accept(this));
        loadIfLocalIndex(division.getRight().accept(this));

        addLine("idiv");

        return null;
    }

    @Override
    public Object visit(Or or){
        loadIfLocalIndex(or.getLeft().accept(this));
        loadIfLocalIndex(or.getRight().accept(this));
        addLine("ior");

        return null;
    }

    @Override
    public Object visit(And and){
        loadIfLocalIndex(and.getLeft().accept(this));
        loadIfLocalIndex(and.getRight().accept(this));
        addLine("iand");

        return null;
    }


    @Override
    public Object visit(Condition condition) {
        loadIfLocalIndex(condition.getConditionExpression().accept(this));

        int ifNumber = this.nextIfNumber++;
        String elseLabel = "else_"+ifNumber;
        String endLabel = "endif_"+ifNumber;

        addLine("ifeq "+elseLabel);
        condition.getThenInstructions().accept(this);
        addLine("goto "+endLabel);

        addLine(elseLabel+":");
        condition.getElseInstructions().accept(this);

        addLine(endLabel+":");

        return null;
    }

    @Override
    public Object visit(WhileLoop whileLoop) {
        int ifNumber = this.nextIfNumber++;
        String beginLabel = "while_"+ifNumber;
        String endLabel = "endwhile_"+ifNumber;

        addLine(beginLabel+":");
        loadIfLocalIndex(whileLoop.getConditionExpression().accept(this));

        addLine("ifeq "+endLabel);
        whileLoop.getInstructions().accept(this);

        addLine("goto "+beginLabel);

        addLine(endLabel+":");

        return null;
    }

    @Override
    public Object visit(ForLoop forLoop) {
        int ifNumber = this.nextIfNumber++;
        String beginLabel = "for_"+ifNumber;
        String endLabel = "endfor_"+ifNumber;

        loadIfLocalIndex(forLoop.getLowerBoundary().accept(this));
        int varLocalIndex = (Integer) forLoop.getVar().accept(this);
        addLine("istore "+varLocalIndex);

        addLine(beginLabel+":");
        addLine("iload "+varLocalIndex);
        loadIfLocalIndex(forLoop.getUpperBoundary().accept(this));

        addLine("if_icmpgt "+endLabel);

        forLoop.getInstructions().accept(this);

        addLine("iinc "+varLocalIndex+" 1");
        addLine("goto "+beginLabel);

        addLine(endLabel+":");

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

            addLine("invokevirtual java/io/PrintStream/println(I)V");
        }
        else if (writeInstr.getExpr().getType().isConform(TypeString.getInstance())) {
            addLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
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
            addLine("iload " + (int)maybeLocalIndex);
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
