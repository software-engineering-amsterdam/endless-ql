import analysis.TypeChecker;
import model.expression.Expression;
import model.expression.binary.ExpressionArithmeticSum;
import model.expression.variable.ExpressionVariableBoolean;
import model.expression.variable.ExpressionVariableInteger;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeCheckerTest {

    private Expression expressionFromString(String input) {
        ANTLRTester tester = new ANTLRTester(input);
        return tester.visitor.visit(tester.parser.expression());
    }

    @Test
    public void SomeHappyTypeCheckTest() {
        Expression expression = this.expressionFromString("1 + 1");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);

        assertEquals(typeChecker.getErrors().size(), 0);
    }

    @Test
    public void SomeSadTypeCheckTest() {
        Expression expression = this.expressionFromString("1 + true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);

        assertEquals(typeChecker.getErrors().size(), 1);
    }
}
