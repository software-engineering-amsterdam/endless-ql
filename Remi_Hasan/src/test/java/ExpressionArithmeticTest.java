import antlr.QLLexer;
import antlr.QLParser;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import model.expression.Expression;
import model.expression.binary.ExpressionArithmeticDivide;
import model.expression.binary.ExpressionArithmeticMultiply;
import model.expression.binary.ExpressionArithmeticSubtract;
import model.expression.binary.ExpressionArithmeticSum;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.variable.ExpressionVariableInteger;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionArithmeticTest {
//
//    private QLParser.ExpressionContext getExpressionContext(String expression) {
//        QLLexer lexer = new QLLexer(CharStreams.fromString(expression));
//        QLParser parser = new QLParser(new CommonTokenStream(lexer));
//
//        return parser.expression();
//    }

    @Property
    public void ExpressionArithmeticDividePositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        ANTLRTester tester = new ANTLRTester(left + " / " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticDivide expectedExpression = new ExpressionArithmeticDivide(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticDivideNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        ANTLRTester tester = new ANTLRTester(left + " / " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionArithmeticDivide expectedExpression = new ExpressionArithmeticDivide(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticMultiplyPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        ANTLRTester tester = new ANTLRTester(left + " * " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticMultiply expectedExpression = new ExpressionArithmeticMultiply(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticMultiplyNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        ANTLRTester tester = new ANTLRTester(left + " * " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionArithmeticMultiply expectedExpression = new ExpressionArithmeticMultiply(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSubtractPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        ANTLRTester tester = new ANTLRTester(left + " - " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticSubtract expectedExpression = new ExpressionArithmeticSubtract(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSubtractNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        ANTLRTester tester = new ANTLRTester(left + " - " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionArithmeticSubtract expectedExpression = new ExpressionArithmeticSubtract(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSumPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticSum expectedExpression = new ExpressionArithmeticSum(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSumNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionArithmeticSum expectedExpression = new ExpressionArithmeticSum(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

}