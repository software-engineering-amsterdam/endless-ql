package qlviz.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

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