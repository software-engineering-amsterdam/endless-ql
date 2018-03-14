package Analysis;

import Parser.ANTLRTester;
import ql.analysis.TypeChecker;
import ql.model.expression.Expression;
import ql.model.expression.binary.ExpressionArithmeticSum;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.expression.variable.ExpressionVariableInteger;
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
    }

    @Test(expected = IllegalArgumentException.class)
    public void SomeSadTypeCheckTest() {
        Expression expression = ANTLRTester.expressionFromString("1 + true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }
}
