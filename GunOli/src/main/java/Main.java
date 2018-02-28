import ParseObjects.Expressions.BinaryExpressions.AdditionExpression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import ParseObjects.Form;

import java.io.InputStream;
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
            Trees.inspect(parser.form(), parser);

            return null;
        }catch(Exception e){
            System.out.println(e);

            return null;
        }
    }

    public void start(){
        String file = "example.ql";
        InputStream stream = getClass().getResourceAsStream(file);
        //Form form = parseForm(stream);

        //Todo: Move to tests
        /*
        IntegerConstant a = new IntegerConstant(3);
        IntegerConstant b = new IntegerConstant(5);
        DecimalConstant c = new DecimalConstant(5.0);
        DecimalConstant d = new DecimalConstant(3.0);

        AdditionExpression intAdd = new AdditionExpression(a,b);
        AdditionExpression mixedAdd = new AdditionExpression(a,c);
        AdditionExpression doubleAdd = new AdditionExpression(c,d);

        System.out.println(a.getValue() + " " + b.getValue() + " " + c.getValue()+ " " + d.getValue());
        System.out.println(intAdd.evaluate().getValue());
        System.out.println(mixedAdd.evaluate().getValue());
        System.out.println(doubleAdd.evaluate().getValue());
        */
    }

    public static void main(String[] args) {
        new Main().start();

    }
}