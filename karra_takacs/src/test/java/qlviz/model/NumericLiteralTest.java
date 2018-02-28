package qlviz.model;

import org.junit.Assert;
import org.junit.Test;
import qlviz.model.numericExpressions.NumericLiteral;

import java.math.BigDecimal;

public class NumericLiteralTest {

    @Test
    public void testEvaluate() {
        // Arrange
        NumericLiteral literal = new NumericLiteral(BigDecimal.valueOf(42));

        // Act

        // Assert
        Assert.assertEquals(BigDecimal.valueOf(42), literal.evaluate());

    }
}