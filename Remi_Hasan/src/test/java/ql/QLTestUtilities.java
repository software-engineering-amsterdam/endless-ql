package ql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.evaluation.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.expression.Expression;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.visitor.VisitorExpression;

import java.io.File;
import java.io.InputStream;

public class QLTestUtilities {

    public static Form buildForm(InputStream inputStream) throws Exception {
        QLEvaluator qlEvaluator = new QLEvaluator(inputStream);
        return qlEvaluator.getForm();
    }

    public static Expression expressionFromString(String input) {
        QLLexer lexer = new QLLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        // Walk it and attach our listener
        VisitorExpression visitorExpression = new VisitorExpression();

        return visitorExpression.visit(parser.expression());
    }

    public static Value evaluateExpression(String input) {
        Expression expression = expressionFromString(input);

        SymbolTable symbolTable = new SymbolTable();
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);

        return interpreterVisitor.visit(expression);
    }
}
