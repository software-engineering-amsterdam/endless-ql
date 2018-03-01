package tool;


import antlr.FormLexer;
import antlr.FormParser;
import domain.model.Question;
import domain.model.visitor.VariableVisitor;
import loader.QLLoader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String [ ] args){
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
            FormLexer lexer = new FormLexer(input);
            FormParser parser = new FormParser(new CommonTokenStream(lexer));
            FormParser.FormBuilderContext tree = parser.formBuilder();
            QLLoader loader = new QLLoader();
            VariableVisitor visitor = new VariableVisitor();
            ParseTreeWalker.DEFAULT.walk(loader, tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
