package main.model;

import org.junit.Assert;
import org.junit.Test;
import qlviz.model.BooleanLiteral;

public class BooleanLiteralTest {

    @Test
    public void testEvaluate() {
       // Arrange
        BooleanLiteral falseLiteral = new BooleanLiteral(false) ;
        BooleanLiteral trueLiteral = new BooleanLiteral(true);

        // Act
        boolean falseResult = falseLiteral.evaluate();
        boolean trueResult = trueLiteral.evaluate();

        // Assert
        Assert.assertFalse(falseResult);
        Assert.assertTrue(trueResult);
    }
}
