import astvisitor.TypeCheckVisitor;
import expression.binary.ExpressionArithmeticSum;
import expression.variable.ExpressionVariableBoolean;
import expression.variable.ExpressionVariableInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeCheckerTest {

    @Test
    public void SomeHappyTypeCheckTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(1);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(intExpression, intExpression);

        TypeCheckVisitor typeChecker = new TypeCheckVisitor(null);
        typeChecker.visit(sumExpression);

        assertEquals(typeChecker.errors.size(), 0);
    }

    @Test
    public void SomeSadTypeCheckTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(1);
        ExpressionVariableBoolean boolExpression = new ExpressionVariableBoolean(true);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(intExpression, boolExpression);

        TypeCheckVisitor typeChecker = new TypeCheckVisitor(null);
        typeChecker.visit(sumExpression);

        assertEquals(typeChecker.errors.size(), 1);
    }
}
