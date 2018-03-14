package ql.Analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.QLTestUtilities;
import ql.analysis.TypeChecker;
import ql.model.expression.Expression;

public class TypeCheckerTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void positiveTypeCheckerTest() {
        Expression expression = QLTestUtilities.expressionFromString("1 + 1");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void negativeTypeCheckerTest() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid addition: non-numeric value in expression");

        Expression expression = QLTestUtilities.expressionFromString("1 + true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }
}
