/* classe 'principale' */

import abstract_tree.AbstractTree;
import abstract_tree.Block;
import symbole_table.ErrorManager;
import visitors.JasminGenerator;
import visitors.SemanticAnalyser;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.io.FileReader;

public class test {
    public static void main(String[] arg) {
        try {
            System.out.println("Starting parsing...");

            FileReader myFile = new FileReader(arg[0]);
            Hepial myLex = new Hepial(myFile);
            parser myP = new parser(myLex);

            try {
                myP.parse();
            } catch (Exception e) {
                System.out.println("Parse error:\n" + e.toString() + ": " + e.getMessage());
                return;
            }

            System.out.println("Parsing: done. Starting semantic analysis...");
            Stack<AbstractTree> treeStack = myP.getTreeStack();
            SemanticAnalyser semanticAnalyser = new SemanticAnalyser();

            System.out.println("Tree stack: "+treeStack);

            Block mainBlock = (Block)treeStack.pop();
            semanticAnalyser.visit(mainBlock);

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
            }
            catch (Exception e) {
                System.out.println("Jasmin generation error:\n" + e.toString() + " : " + e.getMessage());
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    System.out.println(stackTraceElement);
                }
            }

        } catch (Exception e) {
            System.out.println("Invalid file");
        }
    }
}
