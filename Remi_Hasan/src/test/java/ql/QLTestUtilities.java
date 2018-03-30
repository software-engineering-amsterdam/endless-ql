package ql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.expression.Expression;
import ql.builder.ExpressionBuilder;

import java.io.InputStream;

public class QLTestUtilities {

    public static Form buildForm(InputStream inputStream) throws Exception {
        QLForm qlForm = new QLForm(inputStream);
        return qlForm.getForm();
    }

    public static Expression expressionFromString(String input) {
        QLLexer lexer = new QLLexer(CharStreams.fromString(input));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        // Walk it and attach our listener
        ExpressionBuilder expressionBuilder = new ExpressionBuilder();

        return expressionBuilder.visit(parser.expression());
    }

    public static Value evaluateExpression(String input) {
        Expression expression = expressionFromString(input);

        SymbolTable symbolTable = new SymbolTable();
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);

        return interpreterVisitor.visit(expression);
    }
}
