import expression.Expression;
import expression.ExpressionVariableInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpressionArithmeticTest {

    @Test
    public void ExpressionArtihmeticDivide(){
        AntlrTester tester = new AntlrTester("1 / 2");
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(0);

        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }


    @Test(expected = ArithmeticException.class)
    public void ExpressionArtihmeticDivideByZero(){
        AntlrTester tester = new AntlrTester("1.0 / 0.0");
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        actualExpression.evaluate();
    }

    @Test
    public void ExpressionArtihmeticMultiply(){
        AntlrTester tester = new AntlrTester("1 * 2");
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(2);

        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }



}
