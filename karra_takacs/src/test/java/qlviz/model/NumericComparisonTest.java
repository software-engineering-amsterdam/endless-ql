package qlviz.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumericComparisonTest {

    @Test
    public void testEquals() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.Equal);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.Equal);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.Equal);

        // Act

        //Assert
        Assert.assertTrue(equalComparison.evaluate());
        Assert.assertFalse(smallerComparison.evaluate());
        Assert.assertFalse(greaterComparison.evaluate());
    }

    @Test
    public void testNotEquals() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.NotEqual);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.NotEqual);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.NotEqual);

        // Act

        //Assert
        Assert.assertFalse(equalComparison.evaluate());
        Assert.assertTrue(smallerComparison.evaluate());
        Assert.assertTrue(greaterComparison.evaluate());
    }


    @Test
    public void testSmaller() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.Smaller);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.Smaller);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.Smaller);

        // Act

        //Assert
        Assert.assertFalse(equalComparison.evaluate());
        Assert.assertTrue(smallerComparison.evaluate());
        Assert.assertFalse(greaterComparison.evaluate());
    }

    @Test
    public void testGreater() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.Greater);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.Greater);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.Greater);

        // Act

        //Assert
        Assert.assertFalse(equalComparison.evaluate());
        Assert.assertFalse(smallerComparison.evaluate());
        Assert.assertTrue(greaterComparison.evaluate());
    }

    @Test
    public void testSmallerOrEuqal() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.SmallerOrEqual);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.SmallerOrEqual);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.SmallerOrEqual);

        // Act

        //Assert
        Assert.assertTrue(equalComparison.evaluate());
        Assert.assertTrue(smallerComparison.evaluate());
        Assert.assertFalse(greaterComparison.evaluate());
    }

    @Test
    public void testGreaterOrEuqal() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.GreaterOrEqual);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.GreaterOrEqual);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.GreaterOrEqual);

        // Act

        //Assert
        Assert.assertTrue(equalComparison.evaluate());
        Assert.assertFalse(smallerComparison.evaluate());
        Assert.assertTrue(greaterComparison.evaluate());
    }

    @Test
    public void testNotEqual() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        NumericComparison smallerComparison = new NumericComparison(threeMock, fiveMock, NumericComparisonOperator.NotEqual);
        NumericComparison greaterComparison = new NumericComparison(fiveMock, threeMock, NumericComparisonOperator.NotEqual);
        NumericComparison equalComparison = new NumericComparison(threeMock, threeMock, NumericComparisonOperator.NotEqual);

        // Act

        //Assert
        Assert.assertFalse(equalComparison.evaluate());
        Assert.assertTrue(smallerComparison.evaluate());
        Assert.assertTrue(greaterComparison.evaluate());
    }

}