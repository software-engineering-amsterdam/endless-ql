import analysis.TypeChecker;
import model.expression.binary.ExpressionArithmeticSum;
import model.expression.variable.ExpressionVariableBoolean;
import model.expression.variable.ExpressionVariableInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeCheckerTest {

    @Test
    public void SomeHappyTypeCheckTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(null,1);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(null, intExpression, intExpression);

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(sumExpression);

        assertEquals(typeChecker.getErrors().size(), 0);
    }

    @Test
    public void SomeSadTypeCheckTest() {
        ExpressionVariableInteger intExpression = new ExpressionVariableInteger(null, 1);
        ExpressionVariableBoolean boolExpression = new ExpressionVariableBoolean(null, true);
        ExpressionArithmeticSum sumExpression = new ExpressionArithmeticSum(null, intExpression, boolExpression);

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(sumExpression);

        assertEquals(typeChecker.getErrors().size(), 1);
    }
}
