import ParseObjects.Expressions.BinaryExpressions.AdditionExpression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import ParseObjects.Form;

import java.io.InputStream;

import Visitor.FormVisitor;
import antlrGen.*;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

    public Form parseForm(InputStream stream){
        try{
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            Trees.inspect(parser.head(), parser);

            FormVisitor visitor = new FormVisitor();
            return visitor.visit(parser.head());
        }catch(Exception e){
            System.out.println(e);

            return null;
        }
    }

    public void start(){
        String file = "example.ql";
        InputStream stream = getClass().getResourceAsStream(file);
        Form form = parseForm(stream);

    }

    public static void main(String[] args) {
        new Main().start();

    }
}