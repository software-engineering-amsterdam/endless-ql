import astvisitor.BoolValue;
import astvisitor.TypeCheckVisitor;
import expression.binary.ExpressionArithmeticSum;
import expression.variable.ExpressionVariableBoolean;
import expression.variable.ExpressionVariableInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterperationTest {

    @Test
    public void SomeHappyTypeCheckTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(1);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(intExpression, intExpression);

        TypeCheckVisitor visitor = new TypeCheckVisitor();

        assertEquals(visitor.visit(sumExpression), new BoolValue(true));
    }

    @Test
    public void SomeSadTypeCheckTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(1);
        ExpressionVariableBoolean boolExpression = new ExpressionVariableBoolean(true);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(intExpression, boolExpression);

        TypeCheckVisitor visitor = new TypeCheckVisitor();
        assertEquals(visitor.visit(sumExpression), new BoolValue(false));
    }
}
