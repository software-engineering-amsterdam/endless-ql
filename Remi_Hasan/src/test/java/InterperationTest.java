import astvisitor.InterpreterVisitor;
import astvisitor.NumValue;
import expression.binary.ExpressionArithmeticSum;
import expression.variable.ExpressionVariableInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterperationTest {

    @Test
    public void SomeInterpTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(1);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(intExpression, intExpression);

        InterpreterVisitor visitor = new InterpreterVisitor();

        assertEquals(visitor.visit(sumExpression), new NumValue(2));
    }
}
