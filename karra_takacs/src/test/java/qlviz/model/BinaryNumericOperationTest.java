package qlviz.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BinaryNumericOperationTest {

    @Test
    public void testAdd() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        BinaryNumericOperation threeFiveOperation = new BinaryNumericOperation(threeMock, fiveMock, BinaryNumericOperator.Add);
        BinaryNumericOperation fiveThreeOperation = new BinaryNumericOperation(fiveMock, threeMock, BinaryNumericOperator.Add);
        BinaryNumericOperation threeThreeOperation = new BinaryNumericOperation(threeMock, threeMock, BinaryNumericOperator.Add);

        // Act

        //Assert
        Assert.assertEquals(BigDecimal.valueOf(8), threeFiveOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(8), fiveThreeOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(6), threeThreeOperation.evaluate());
    }


    @Test
    public void testSubtract() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        BinaryNumericOperation threeFiveOperation = new BinaryNumericOperation(threeMock, fiveMock, BinaryNumericOperator.Subtract);
        BinaryNumericOperation fiveThreeOperation = new BinaryNumericOperation(fiveMock, threeMock, BinaryNumericOperator.Subtract);
        BinaryNumericOperation threeThreeOperation = new BinaryNumericOperation(threeMock, threeMock, BinaryNumericOperator.Subtract);

        // Act

        //Assert
        Assert.assertEquals(BigDecimal.valueOf(-2), threeFiveOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(2), fiveThreeOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(0), threeThreeOperation.evaluate());
    }

    @Test
    public void testMultiply() {
        // Arrange
        NumericExpression threeMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(threeMock.evaluate()).thenReturn(BigDecimal.valueOf(3));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        BinaryNumericOperation threeFiveOperation = new BinaryNumericOperation(threeMock, fiveMock, BinaryNumericOperator.Multiply);
        BinaryNumericOperation fiveThreeOperation = new BinaryNumericOperation(fiveMock, threeMock, BinaryNumericOperator.Multiply);
        BinaryNumericOperation threeThreeOperation = new BinaryNumericOperation(threeMock, threeMock, BinaryNumericOperator.Multiply);

        // Act

        //Assert
        Assert.assertEquals(BigDecimal.valueOf(15), threeFiveOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(15), fiveThreeOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(9), threeThreeOperation.evaluate());
    }

    @Test
    public void testDivide() {
        // Arrange
        NumericExpression twoMock = mock(NumericExpression.class);
        NumericExpression fiveMock = mock(NumericExpression.class);

        when(twoMock.evaluate()).thenReturn(BigDecimal.valueOf(2));
        when(fiveMock.evaluate()).thenReturn(BigDecimal.valueOf(5));

        BinaryNumericOperation twoFiveOperation = new BinaryNumericOperation(twoMock, fiveMock, BinaryNumericOperator.Divide);
        BinaryNumericOperation fiveTwoOperation = new BinaryNumericOperation(fiveMock, twoMock, BinaryNumericOperator.Divide);
        BinaryNumericOperation twoTwoOperation = new BinaryNumericOperation(twoMock, twoMock, BinaryNumericOperator.Divide);

        // Act

        //Assert
        Assert.assertEquals(BigDecimal.valueOf(0.4), twoFiveOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(2.5), fiveTwoOperation.evaluate());
        Assert.assertEquals(BigDecimal.valueOf(1), twoTwoOperation.evaluate());
    }

}
