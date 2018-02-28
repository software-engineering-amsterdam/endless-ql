package qlviz.model;

import org.junit.Assert;
import org.junit.Test;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.numericExpressions.NumericNegation;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumericNegationTest {

    @Test
    public void testEvaluate() {
        // Arrange
        NumericExpression positiveMock = mock(NumericExpression.class);
        NumericExpression negativeMock = mock(NumericExpression.class);

        when(positiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));
        when(negativeMock.evaluate()).thenReturn(BigDecimal.valueOf(-5));


        NumericExpression negatePositive = new NumericNegation(positiveMock);
        NumericExpression negateNegative = new NumericNegation(negativeMock);

        // Act

        // Assert
        Assert.assertEquals(BigDecimal.valueOf(-5), negatePositive.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(5), negateNegative.evaluate());
    }
}