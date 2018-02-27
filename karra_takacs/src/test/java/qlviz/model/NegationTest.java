package qlviz.model;

import org.junit.Assert;
import org.junit.Test;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.booleanExpressions.Negation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NegationTest {

    @Test
    public void testEvaluate() {
        // Arrange
        BooleanExpression falseMock = mock(BooleanExpression.class);
        BooleanExpression trueMock = mock(BooleanExpression.class);
        when(falseMock.evaluate()).thenReturn(false);
        when(trueMock.evaluate()).thenReturn(true);

        Negation negateTrue = new Negation(trueMock);
        Negation negateFalse = new Negation(falseMock);

        // Act

        // Assert
        Assert.assertFalse(negateTrue.evaluate());
        Assert.assertTrue(negateFalse.evaluate());

    }
}