import classes.form.Form;
import gui.FormBuilder;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parsing.AST_Visitor;
import parsing.gen.QLLexer;
import parsing.gen.QLParser;
import typechecking.TypeChecker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    /**
     * parse and build the form file
     * @param inputStream - input stream of the given form file
     */
    public void parseAndBuild(InputStream inputStream){
        try{
            //Call the lexer and get the tokens
            QLLexer lexer = new QLLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            //Parse the tokens/tree
            QLParser parser = new QLParser(tokens);

            //Call the visitor and build the tree
            AST_Visitor builder = new AST_Visitor();
            QLParser.FormContext tree = parser.form();
            Form form = (Form) builder.visit(tree);
            //Test output
//            Iterator it = memory.entrySet().iterator();
//            while (it.hasNext()) {
//                  Map.Entry pair = (Map.Entry)it.next();
//                  System.out.println(pair.getKey() + " = " + pair.getValue());
//                  it.remove();
//            }
//            System.out.println("done");

            //Call parse tree inspector: Show the tree
            //Trees.inspect(tree, parser);

            //Do typechecking
            //TypeChecker typeChecker = new TypeChecker();
            //typeChecker.initTypeChecking(form);

            //Pass the relevant questions to the UI builder
            FormBuilder formBuilder = new FormBuilder(builder);
            formBuilder.initComponents(form);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Main method
     * @param args given arguments
     */
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                new Main().parseAndBuild(System.in);
            } else if (args.length == 1) {
                FileInputStream fileInputStream = new FileInputStream(args[0]);
                new Main().parseAndBuild(fileInputStream);
            } else {
                System.out.println("Invalid arguments were given");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
