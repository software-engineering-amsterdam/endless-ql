package ql.test.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BoolLiteral;
import ql.ast.expression.DecimalLiteral;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.IntLiteral;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.MoneyLiteral;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.StrLiteral;
import ql.ast.expression.Subtract;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;

public class TestParser {
    
    private QLTestParser parser = new QLTestParser();
    
    @Test
    public void testForm() {
        String form0 = "form empty {}";
        String form1 = "form invalid { if(condition) \"Answerable question\" answerable: integer }";
        String form2 = "form valid { "
                        + "if(condition) {"
                        + "\"Answerable question 1\" answerable1: integer"
                        + "\"Computed question 1\" computed: string = (some + expr * 0)"
                        + "\"Answerable question 2\" answerable2: integer"
                        + "} "
                        + "\"Computed question 4\" computed: string = (some + expr * 0)"
                        + "if(condition) {} else {}"
                        + "}";
        assertEquals(parser.parseForm(form0).getClass(),Form.class);
        assertEquals(parser.parseForm(form1).getClass(),Form.class);
        assertEquals(parser.parseForm(form2).getClass(),Form.class);
    }
    
    @Test
    public void testBlock() {
        String stmt0 = "{}";
        String stmt1 = "{ if(condition) \"Answerable question\" answerable: integer }";
        String stmt2 = "{ "
                        + "if(condition) {"
                        + "\"Answerable question 1\" answerable1: integer"
                        + "\"Computed question 1\" computed: string = (some + expr * 0)"
                        + "\"Answerable question 2\" answerable2: integer"
                        + "} "
                        + "\"Computed question 4\" computed: string = (some + expr * 0)"
                        + "if(condition) {} else {}"
                        + "}";
        assertEquals(parser.parseStmt(stmt0).getClass(),Block.class);
        assertEquals(parser.parseStmt(stmt1).getClass(),Block.class);
        assertEquals(parser.parseStmt(stmt2).getClass(),Block.class);
    }
    
    @Test
    public void testIfThen() {
        String stmt0 = "if(condition) {}";
        String stmt1 = "if(condition) \"Answerable question\" answerable: integer";
        String stmt2 = "if(condition) {"
                        + "\"Answerable question 1\" answerable1: integer"
                        + "\"Answerable question 2\" answerable2: integer"
                        + "\"Answerable question 3\" answerable3: integer"
                        + "}";
        assertEquals(parser.parseStmt(stmt0).getClass(),IfThen.class);
        assertEquals(parser.parseStmt(stmt1).getClass(),IfThen.class);
        assertEquals(parser.parseStmt(stmt2).getClass(),IfThen.class);
    }
    
    @Test
    public void testIfThenElse() {
        String stmt0 = "if(condition) {} else {}";
        String stmt1 = "if(condition) "
                        + "\"Answerable question\" answerable: integer"
                        + " else "
                        + "\"Computed question\" computed: string = (some + expr * 0)";
        String stmt2 = "if(condition) "
                        + "\"Answerable question\" answerable: integer"
                        + " else if(condition2)"
                        + "\"Computed question\" computed: string = (some + expr * 0)";
        String stmt3 = "if(condition) {"
                        + "\"Answerable question 1\" answerable1: integer"
                        + "\"Answerable question 2\" answerable2: integer"
                        + "\"Answerable question 3\" answerable3: integer"
                        + "} else {"
                        + "\"Computed question 1\" computed1: string = (some + expr * 0) }"
                        + "\"Computed question 2\" computed2: string = (some + expr * 0) }";
        assertEquals(parser.parseStmt(stmt0).getClass(),IfThenElse.class);
        assertEquals(parser.parseStmt(stmt1).getClass(),IfThenElse.class);
        assertEquals(parser.parseStmt(stmt2).getClass(),IfThenElse.class);
        assertEquals(parser.parseStmt(stmt3).getClass(),IfThenElse.class);
    }
    
    @Test
    public void testAnswerableQuestion() {
        assertEquals(parser.parseStmt("\"Answerable question\" answerable: integer").getClass(),AnswerableQuestion.class);
    }
    
    @Test
    public void testComputedQuestion() {
        assertEquals(parser.parseStmt("\"Computed question\" computed: string = (some + expr * 0)").getClass(),ComputedQuestion.class);
    }

    @Test
    public void testIdentifier() {
        assertEquals(parser.parseIdentifier("a").getClass(),Identifier.class);
        assertEquals(parser.parseIdentifier("A").getClass(),Identifier.class);
        assertEquals(parser.parseIdentifier("aBcD9_10").getClass(),Identifier.class);
        assertEquals(parser.parseIdentifier("Z1BcD910").getClass(),Identifier.class);
        assertFalse(parser.parseIdentifier("_Z1BcD910").toString().equals("_Z1BcD910"));
        assertTrue(parser.parseIdentifier("8Z1BcD910").toString().equals("8Z1BcD910"));
    }

    @Test
    public void testType() {
        assertEquals(parser.parseType("boolean").getClass(),Bool.class);
        assertEquals(parser.parseType("string").getClass(),Str.class);
        assertEquals(parser.parseType("integer").getClass(),Int.class);
        assertEquals(parser.parseType("decimal").getClass(),Decimal.class);
        assertEquals(parser.parseType("money").getClass(),Money.class);
        assertEquals(parser.parseType("date").getClass(),Date.class);
    }

    @Test
    public void testBoolLiteral() {
        assertEquals(parser.parseExpr("true").getClass(),BoolLiteral.class);
        assertEquals(parser.parseExpr("false").getClass(),BoolLiteral.class);
    }
    
    @Test
    public void testStrLiteral() {
        assertEquals(parser.parseExpr("\"This is a string!?\"").getClass(),StrLiteral.class);
        assertEquals(parser.parseExpr("\"This is \"a\" string!?\"").getClass(),StrLiteral.class);
        assertEquals(parser.parseExpr("\"\"").getClass(),StrLiteral.class);
    }

    @Test
    public void testIntLiteral() {
        assertEquals(parser.parseExpr("1").getClass(),IntLiteral.class);
        assertEquals(parser.parseExpr("1111").getClass(),IntLiteral.class);
    }
    
    @Test
    public void testDecimalLiteral() {
//        TODO: resolve money vs. decimal issue
//        assertEquals(parser.parseExpr("0.00").getClass(),DecimalLiteral.class);
        assertEquals(parser.parseExpr(".00").getClass(),DecimalLiteral.class);
        assertEquals(parser.parseExpr(".0").getClass(),DecimalLiteral.class);
        assertEquals(parser.parseExpr("1.0000").getClass(),DecimalLiteral.class);
    }
    
//    TODO: resolve money vs. decimal issue
    @Test
    public void testMoneyLiteral() {
        assertEquals(parser.parseExpr("0.00").getClass(),MoneyLiteral.class);
    }
    
//    TODO: how should dates be formatted?
    @Test
    public void testDateLiteral() {
//        assertEquals(parser.parseExpr("00-00-0000").getClass(),DateLiteral.class);
    }
    
    @Test
    public void testPositive() {
        assertEquals(parser.parseExpr("+1").getClass(),Positive.class);
        assertEquals(parser.parseExpr("+x").getClass(),Positive.class);
        assertEquals(parser.parseExpr("+-x").getClass(),Positive.class);
        assertEquals(parser.parseExpr("+-+x").getClass(),Positive.class);
        assertEquals(parser.parseExpr("+++x").getClass(),Positive.class);
    }

    @Test
    public void testNegative() {
        assertEquals(parser.parseExpr("-1").getClass(),Negative.class);
        assertEquals(parser.parseExpr("-x").getClass(),Negative.class);
        assertEquals(parser.parseExpr("-+x").getClass(),Negative.class);
        assertEquals(parser.parseExpr("-+-x").getClass(),Negative.class);
        assertEquals(parser.parseExpr("---x").getClass(),Negative.class);
    }
    
    @Test
    public void testAdd() {
        assertEquals(parser.parseExpr("1 + 1").getClass(),Add.class);
        assertEquals(parser.parseExpr("-1 + -1").getClass(),Add.class);
        assertEquals(parser.parseExpr("1 + 1 / 1").getClass(),Add.class);
        assertEquals(parser.parseExpr("1 * 1 + 1 / 1").getClass(),Add.class);
        assertEquals(parser.parseExpr("1 - 1 + 1").getClass(),Add.class);
        assertNotEquals(parser.parseExpr("1 + 1 - 1").getClass(),Add.class);
    }
    
    @Test
    public void testSubtract() {
        assertEquals(parser.parseExpr("1 - 1").getClass(),Subtract.class);
        assertEquals(parser.parseExpr("-1 - -1").getClass(),Subtract.class);
        assertEquals(parser.parseExpr("1 - 1 / 1").getClass(),Subtract.class);
        assertEquals(parser.parseExpr("1 * 1 - 1 / 1").getClass(),Subtract.class);
        assertEquals(parser.parseExpr("1 - 1 - 1").getClass(),Subtract.class);
        assertNotEquals(parser.parseExpr("1 - 1 + 1").getClass(),Subtract.class);
    }
    
    @Test
    public void testMultiply() {
        assertEquals(parser.parseExpr("1 * 1").getClass(),Multiply.class);
        assertEquals(parser.parseExpr("-1 * -1").getClass(),Multiply.class);
        assertEquals(parser.parseExpr("1 / 1 * 1").getClass(),Multiply.class);
        assertNotEquals(parser.parseExpr("1 * 1 / 1").getClass(),Multiply.class);
    }
    
    @Test
    public void testDivide() {
        assertEquals(parser.parseExpr("1 / 1").getClass(),Divide.class);
        assertEquals(parser.parseExpr("-1 / -1").getClass(),Divide.class);
        assertEquals(parser.parseExpr("1 * 1 / 1").getClass(),Divide.class);
        assertNotEquals(parser.parseExpr("1 / 1 * 1").getClass(),Divide.class);
    }
    
    @Test
    public void testNot() {
        assertEquals(parser.parseExpr("!true").getClass(),Negation.class);
        assertEquals(parser.parseExpr("!identifier").getClass(),Negation.class);
        assertEquals(parser.parseExpr("!(A + B && C / c)").getClass(),Negation.class);
    }
    
    @Test
    public void testAnd() {
        assertEquals(parser.parseExpr("true && false").getClass(),And.class);
        assertEquals(parser.parseExpr("A && B").getClass(),And.class);
        assertEquals(parser.parseExpr("A != B && C == c").getClass(),And.class);
        assertEquals(parser.parseExpr("!A + B && C / c").getClass(),And.class);
    }
    
    @Test
    public void testOr() {
        assertEquals(parser.parseExpr("true || false").getClass(),Or.class);
        assertEquals(parser.parseExpr("A || B").getClass(),Or.class);
        assertEquals(parser.parseExpr("A != B && C == D || E").getClass(),Or.class);
        assertEquals(parser.parseExpr("!A + B || C / c").getClass(),Or.class);
    }
    
    @Test
    public void testEqual() {
        assertEquals(parser.parseExpr("true == false").getClass(),Equal.class);
        assertEquals(parser.parseExpr("A == B").getClass(),Equal.class);
        assertEquals(parser.parseExpr("A + B == C / D").getClass(),Equal.class);
    }
    
    @Test
    public void testNotEqual() {
        assertEquals(parser.parseExpr("true != false").getClass(),NotEqual.class);
        assertEquals(parser.parseExpr("A != B").getClass(),NotEqual.class);
        assertEquals(parser.parseExpr("A + B != C / D").getClass(),NotEqual.class);
    }
    
    @Test
    public void testLess() {
        assertEquals(parser.parseExpr("true < false").getClass(),Less.class);
        assertEquals(parser.parseExpr("A < B").getClass(),Less.class);
        assertEquals(parser.parseExpr("A + B < C / D").getClass(),Less.class);
    }
    
    @Test
    public void testLessEqual() {
        assertEquals(parser.parseExpr("true <= false").getClass(),LessEqual.class);
        assertEquals(parser.parseExpr("A <= B").getClass(),LessEqual.class);
        assertEquals(parser.parseExpr("A + B <= C / D").getClass(),LessEqual.class);
    }
    
    @Test
    public void testGreater() {
        assertEquals(parser.parseExpr("true > false").getClass(),Greater.class);
        assertEquals(parser.parseExpr("A > B").getClass(),Greater.class);
        assertEquals(parser.parseExpr("A + B > C / D").getClass(),Greater.class);
    }
    
    @Test
    public void testGreaterEqual() {
        assertEquals(parser.parseExpr("true >= false").getClass(),GreaterEqual.class);
        assertEquals(parser.parseExpr("A >= B").getClass(),GreaterEqual.class);
        assertEquals(parser.parseExpr("A + B >= C / D").getClass(),GreaterEqual.class);
    }
}
