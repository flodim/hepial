import abstract_tree.AbstractTree;
import abstract_tree.Block;
import symbole_table.ErrorManager;
import visitors.JasminGenerator;
import visitors.SemanticAnalyser;

import java.io.*;
import java.util.List;
import java.util.Stack;

public class test {
    public static void main(String[] arg) {

        System.out.println("Starting parsing...");

        FileReader myFile = null;
        try {
            myFile = new FileReader(arg[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: "+arg[0]);
            return;
        }
        Hepial myLex = new Hepial(myFile);
        parser myP = new parser(myLex);

        try {
            myP.parse();
        }
        catch (Exception e) {
            System.out.println("Parse error:\n" + e.toString() + " : " + e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            return;
        }

        Stack<AbstractTree> treeStack = myP.getTreeStack();

        System.out.println("Tree stack: "+treeStack);

        Block mainBlock = (Block)treeStack.pop();

        System.out.println("Parsing: done. Starting semantic analysis...");

        SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
        try {
            semanticAnalyser.visit(mainBlock);
        }
        catch (Exception e) {
            System.out.println("Jasmin generation error:\n" + e.toString() + " : " + e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            return;
        }


        List<String> errors = ErrorManager.getInstance().getErrors();
        if (errors.size() != 0)
        {
            System.out.println("Semantic errors:");
            for (String error : errors) {
                System.out.println(error);
            }
            return;
        }


        try {
            System.out.println("Semantic analysis: done. Starting jasmin generation...");
            JasminGenerator jasminGenerator = new JasminGenerator();
            String jasminCode = jasminGenerator.generate(mainBlock);

            System.out.println("Generated jasmin:");
            System.out.println(jasminCode);

            System.out.println("Writing jasmin to output file \"HepialProgram.j\"");
            File file = new File("HepialProgram.j");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(jasminCode);
            }

        }
        catch (Exception e) {
            System.out.println("Jasmin generation error:\n" + e.toString() + " : " + e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
        }

        System.out.println();
    }
}
