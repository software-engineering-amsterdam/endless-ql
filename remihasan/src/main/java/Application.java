import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Application extends JFrame{

    private QLLexer lexer;
    private CommonTokenStream tokens;
    private QLParser parser;

    public static void main(String[] args){
        String fileName = "example.ql";
        Application application = new Application(fileName);
        application.view();
    }

    Application(String fileName){
        try{
            // Create inptustream of QL file that we want to parse
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(file));

            // Inspired by book referenced here: https://github.com/antlr/antlr4/blob/master/doc/java-target.md

            // Create a QL lexer
            lexer = new QLLexer(input);

            // Create a buffer of tokens
            tokens = new CommonTokenStream(lexer);

            // Create a QL parser that uses the tokens to parse
            parser = new QLParser(tokens);

            ParseTree rootTree = parser.r();
            processParseTree(rootTree);
        } catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void processParseTree(ParseTree tree){
//        System.out.println(tree.toStringTree(parser)); // print LISP-style tree

//        System.out.println(tree.getChildCount() + " " + tree.getChild(0));
        for(int childId = 0; childId < tree.getChildCount(); childId++){
            ParseTree child = tree.getChild(childId);
            System.out.println("payload:" + child.getPayload());
            System.out.println("parent: " + child.getParent());
            System.out.println("");

            processParseTree(child);
        }
    }

    private void view() {
        // Do jframe stuff
        this.setSize(500, 500);
//        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
