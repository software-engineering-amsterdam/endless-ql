import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import parsing.QLBuilder;
import parsing.gen.QLLexer;
import parsing.gen.QLParser;

import java.io.*;

public class Main {
    public void parseAndBuild(InputStream inputStream){
        try{
            QLLexer lexer = new QLLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            ParseTree tree = parser.form();

            QLBuilder builder = new QLBuilder();
            builder.visit(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Main method
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
