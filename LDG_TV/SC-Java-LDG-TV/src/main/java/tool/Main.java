package tool;


import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import domain.model.Condition;
import domain.model.IfASTNode;
import domain.visitor.UIVisitor;
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
            UIVisitor visitor = new UIVisitor();
            ParseTreeWalker.DEFAULT.walk(loader, tree);
            IfASTNode f =(IfASTNode) loader.getFormNode().getASTNodes().get(3);
            System.out.println(f.checkConditions());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
