package qlviz.interpreter;

import org.junit.Test;
import qlviz.QLBaseVisitor;
import qlviz.model.numericExpressions.NumericExpression;

import static org.mockito.Mockito.mock;

public class BooleanExpressionVisitorTest {

    @Test
    public void testPrecedence() {

        // Arrange
        QLBaseVisitor<NumericExpression> numericVisitorMock = mock(QLBaseVisitor.class);
        var binaryOperatorTranslator = new BinaryBooleanOperatorVisitor();
        var numericComparisonOperatorVisitor = new NumericComparisonOperatorVisitor();
        var expressionVisitor = new BooleanExpressionParser(
                numericVisitorMock,
                binaryOperatorTranslator,
                numericComparisonOperatorVisitor);
    }
}
