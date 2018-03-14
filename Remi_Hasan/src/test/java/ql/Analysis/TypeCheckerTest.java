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
    public void integerSum() {
        Expression expression = QLTestUtilities.expressionFromString("1 + 1");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerDecimalSum() {
        Expression expression = QLTestUtilities.expressionFromString("1 + 1.0");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerMoneySum() {
        Expression expression = QLTestUtilities.expressionFromString("1 + 1.00");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerBooleanSum() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid addition: non-numeric value in expression");

        Expression expression = QLTestUtilities.expressionFromString("1 + true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void decimalBooleanSum() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid addition: non-numeric value in expression");

        Expression expression = QLTestUtilities.expressionFromString("1.0 + true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerNegation() {
        Expression expression = QLTestUtilities.expressionFromString("-1");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void decimalNegation() {
        Expression expression = QLTestUtilities.expressionFromString("-1.0");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void moneyNegation() {
        Expression expression = QLTestUtilities.expressionFromString("-1.00");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void stringNegation() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid negation: non-numeric expression");

        Expression expression = QLTestUtilities.expressionFromString("-\"test\"");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerComparison() {
        Expression expression = QLTestUtilities.expressionFromString("1 > 2");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerStringComparison() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid GT: non-numeric value in expression");

        Expression expression = QLTestUtilities.expressionFromString("1 > \"string\"");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerNot() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid NOT: non-boolean expression");

        Expression expression = QLTestUtilities.expressionFromString("!2");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void stringNot() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid NOT: non-boolean expression");

        Expression expression = QLTestUtilities.expressionFromString("!\"string\"");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void booleanNot() {
        Expression expression = QLTestUtilities.expressionFromString("!true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerAnd() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid AND: non-boolean value in expression");

        Expression expression = QLTestUtilities.expressionFromString("2 && true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void integerOr() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Invalid OR: non-boolean value in expression");

        Expression expression = QLTestUtilities.expressionFromString("2 || true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void booleanAnd() {
        Expression expression = QLTestUtilities.expressionFromString("false && true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }

    @Test
    public void booleanOr() {
        Expression expression = QLTestUtilities.expressionFromString("false || true");

        TypeChecker typeChecker = new TypeChecker(null, null);
        typeChecker.visit(expression);
    }
}
