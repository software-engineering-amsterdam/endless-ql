package Expression;

import Parser.ANTLRTester;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import model.expression.Expression;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.variable.ExpressionVariableInteger;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionComparisonTest {

    @Property
    public void ExpressionComparisonEqPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " == " + right);

        ExpressionComparisonEq expectedExpression = new ExpressionComparisonEq(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonEqNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " == " + right);

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionComparisonEq expectedExpression = new ExpressionComparisonEq(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGEPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " >= " + right);

        ExpressionComparisonGE expectedExpression = new ExpressionComparisonGE(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGENegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " >= " + right);

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionComparisonGE expectedExpression = new ExpressionComparisonGE(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGTPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " > " + right);

        ExpressionComparisonGT expectedExpression = new ExpressionComparisonGT(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGTNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " > " + right);

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionComparisonGT expectedExpression = new ExpressionComparisonGT(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLEPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " <= " + right);

        ExpressionComparisonLE expectedExpression = new ExpressionComparisonLE(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLENegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " <= " + right);

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionComparisonLE expectedExpression = new ExpressionComparisonLE(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLTPositive(@InRange(min="0") int left, @InRange(min="0") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " < " + right);

        ExpressionComparisonLT expectedExpression = new ExpressionComparisonLT(null,
                new ExpressionVariableInteger(null, left),  new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLTNegative(@InRange(max="-1") int left, @InRange(max="-1") int right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " < " + right);

        Expression leftExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(left)));
        Expression rightExpression = new ExpressionUnaryNeg(null, new ExpressionVariableInteger(null, Math.abs(right)));

        ExpressionComparisonLT expectedExpression = new ExpressionComparisonLT(null, leftExpression, rightExpression);
        assertEquals(expectedExpression, actualExpression);
    }

}
