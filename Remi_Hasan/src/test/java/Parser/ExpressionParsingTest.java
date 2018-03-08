package Parser;

import antlr.QLLexer;
import antlr.QLParser;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionParsingTest {
    private QLParser.ExpressionContext getExpressionContext(String expression) {
        QLLexer lexer = new QLLexer(CharStreams.fromString(expression));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        return parser.expression();
    }

    private void basicExpression(int left, int right, String operator) {
        QLParser.ExpressionContext expressionContext = getExpressionContext(left + operator + right);

        assertEquals(expressionContext.getChild(0).getText(), String.valueOf(left));
        assertEquals(expressionContext.getChild(1).getText(), operator);
        assertEquals(expressionContext.getChild(2).getText(), String.valueOf(right));
    }

    @Property
    public void expressionAdd(int left, int right) {
        basicExpression(left, right, "+");
    }

    @Property
    public void expressionSub(int left, int right) {
        basicExpression(left, right, "-");
    }

    @Property
    public void expressionMul(int left, int right) {
        basicExpression(left, right, "*");
    }

    @Property
    public void expressionDiv(int left, int right) {
        basicExpression(left, right, "/");
    }

    @Property
    public void expressionGT(int left, int right) {
        basicExpression(left, right, ">");
    }

    @Property
    public void expressionGE(int left, int right) {
        basicExpression(left, right, ">=");
    }

    @Property
    public void expressionLT(int left, int right) {
        basicExpression(left, right, "<");
    }

    @Property
    public void expressionLE(int left, int right) {
        basicExpression(left, right, "<=");
    }

    @Property
    public void expressionEQ(int left, int right) {
        basicExpression(left, right, "==");
    }

    @Property
    public void expressionNE(int left, int right) {
        basicExpression(left, right, "!=");
    }

    @Property
    public void expressionAND(int left, int right) {
        basicExpression(left, right, "&&");
    }

    @Property
    public void expressionOR(int left, int right) {
        basicExpression(left, right, "||");
    }
}