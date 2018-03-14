package Parser;

import ql.analysis.SymbolTable;
import ql.parser.QLLexer;
import ql.parser.QLParser;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.visitor.VisitorExpression;

public class ANTLRTester {

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
