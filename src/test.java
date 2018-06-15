/* classe 'principale' */

import abstract_tree.AbstractTree;
import visitors.SemanticAnalyser;

import java.util.Stack;
import java.util.Vector;
import java.io.FileReader;

public class test {
    public static void main(String[] arg) {
        try {
            FileReader myFile = new FileReader(arg[0]);
            Hepial myLex = new Hepial(myFile);
            parser myP = new parser(myLex);

            try {
                myP.parse();
            } catch (Exception e) {
                System.out.println("parse error:\n" + e.toString() + ": " + e.getMessage());
                return;
            }

            Stack<AbstractTree> treeStack = myP.getTreeStack();
            SemanticAnalyser semanticAnalyser = new SemanticAnalyser();

            System.out.println(treeStack);
        } catch (Exception e) {
            System.out.println("invalid file");
        }
    }
}
