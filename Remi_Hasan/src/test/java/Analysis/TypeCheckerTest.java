package Analysis;

import Parser.ANTLRTester;
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

    @Test
    public void SomeHappyTypeCheckTest() {
        Expression expression = ANTLRTester.expressionFromString("1 + 1");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);

        assertEquals(typeChecker.getErrors().size(), 0);
    }

    @Test
    public void SomeSadTypeCheckTest() {
        Expression expression = ANTLRTester.expressionFromString("1 + true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);

        assertEquals(typeChecker.getErrors().size(), 1);
    }
}
