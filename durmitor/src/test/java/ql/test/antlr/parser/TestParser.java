package ql.test.antlr.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.Subtract;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
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
        assertEquals(Form.class, parser.parseForm(form0).getClass());
        assertEquals(Form.class, parser.parseForm(form1).getClass());
        assertEquals(Form.class, parser.parseForm(form2).getClass());
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
        assertEquals(Block.class, parser.parseStmt(stmt0).getClass());
        assertEquals(Block.class, parser.parseStmt(stmt1).getClass());
        assertEquals(Block.class, parser.parseStmt(stmt2).getClass());
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
        assertEquals(IfThen.class, parser.parseStmt(stmt0).getClass());
        assertEquals(IfThen.class, parser.parseStmt(stmt1).getClass());
        assertEquals(IfThen.class, parser.parseStmt(stmt2).getClass());
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
        assertEquals(IfThenElse.class, parser.parseStmt(stmt0).getClass());
        assertEquals(IfThenElse.class, parser.parseStmt(stmt1).getClass());
        assertEquals(IfThenElse.class, parser.parseStmt(stmt2).getClass());
        assertEquals(IfThenElse.class, parser.parseStmt(stmt3).getClass());
    }
    
    @Test
    public void testAnswerableQuestion() {
        assertEquals(AnswerableQuestion.class, parser.parseStmt("\"Answerable question\" answerable: integer").getClass());
    }
    
    @Test
    public void testComputedQuestion() {
        assertEquals(ComputedQuestion.class, parser.parseStmt("\"Computed question\" computed: string = (some + expr * 0)").getClass());
    }

    @Test
    public void testIdentifier() {
        assertEquals(Identifier.class, parser.parseIdentifier("a").getClass());
        assertEquals(Identifier.class, parser.parseIdentifier("A").getClass());
        assertEquals(Identifier.class, parser.parseIdentifier("aBcD9_10").getClass());
        assertEquals(Identifier.class, parser.parseIdentifier("Z1BcD910").getClass());
        assertFalse(parser.parseIdentifier("_Z1BcD910").toString().equals("_Z1BcD910"));
        assertTrue(parser.parseIdentifier("8Z1BcD910").toString().equals("8Z1BcD910"));
    }

    @Test
    public void testType() {
        assertEquals(Bool.class, parser.parseType("boolean").getClass());
        assertEquals(Str.class, parser.parseType("string").getClass());
        assertEquals(Int.class, parser.parseType("integer").getClass());
        assertEquals(Decimal.class, parser.parseType("decimal").getClass());
        assertEquals(Money.class, parser.parseType("money").getClass());
        assertEquals(Date.class, parser.parseType("date").getClass());
    }

    @Test
    public void testBoolLiteral() {
        assertEquals(BoolLiteral.class, parser.parseExpr("true").getClass());
        assertEquals(BoolLiteral.class, parser.parseExpr("false").getClass());
    }
    
    @Test
    public void testStrLiteral() {
        assertEquals(StrLiteral.class, parser.parseExpr("\"This is a string!?\"").getClass());
        assertEquals(StrLiteral.class, parser.parseExpr("\"This is \"a\" string!?\"").getClass());
        assertEquals(StrLiteral.class, parser.parseExpr("\"\"").getClass());
    }

    @Test
    public void testIntLiteral() {
        assertEquals(IntLiteral.class, parser.parseExpr("1").getClass());
        assertEquals(IntLiteral.class, parser.parseExpr("1111").getClass());
    }
    
    @Test
    public void testDecimalLiteral() {
//        TODO: resolve money vs. decimal issue
//        assertEquals(DecimalLiteral.class, parser.parseExpr("0.00").getClass());
        assertEquals(DecimalLiteral.class, parser.parseExpr(".00").getClass());
        assertEquals(DecimalLiteral.class, parser.parseExpr(".0").getClass());
        assertEquals(DecimalLiteral.class, parser.parseExpr("1.0000").getClass());
    }
    
//    TODO: resolve money vs. decimal issue
    @Test
    public void testMoneyLiteral() {
        assertEquals(MoneyLiteral.class, parser.parseExpr("0.00").getClass());
    }
    
//    TODO: how should dates be formatted?
    @Test
    public void testDateLiteral() {
//        assertEquals(DateLiteral.class, parser.parseExpr("00-00-0000").getClass());
    }
    
    @Test
    public void testPositive() {
        assertEquals(Positive.class, parser.parseExpr("+1").getClass());
        assertEquals(Positive.class, parser.parseExpr("+x").getClass());
        assertEquals(Positive.class, parser.parseExpr("+-x").getClass());
        assertEquals(Positive.class, parser.parseExpr("+-+x").getClass());
        assertEquals(Positive.class, parser.parseExpr("+++x").getClass());
    }

    @Test
    public void testNegative() {
        assertEquals(Negative.class, parser.parseExpr("-1").getClass());
        assertEquals(Negative.class, parser.parseExpr("-x").getClass());
        assertEquals(Negative.class, parser.parseExpr("-+x").getClass());
        assertEquals(Negative.class, parser.parseExpr("-+-x").getClass());
        assertEquals(Negative.class, parser.parseExpr("---x").getClass());
    }
    
    @Test
    public void testAdd() {
        assertEquals(Add.class, parser.parseExpr("1 + 1").getClass());
        assertEquals(Add.class, parser.parseExpr("-1 + -1").getClass());
        assertEquals(Add.class, parser.parseExpr("1 + 1 / 1").getClass());
        assertEquals(Add.class, parser.parseExpr("1 * 1 + 1 / 1").getClass());
        assertEquals(Add.class, parser.parseExpr("1 - 1 + 1").getClass());
        assertNotEquals(parser.parseExpr("1 + 1 - 1").getClass(),Add.class);
    }
    
    @Test
    public void testSubtract() {
        assertEquals(Subtract.class, parser.parseExpr("1 - 1").getClass());
        assertEquals(Subtract.class, parser.parseExpr("-1 - -1").getClass());
        assertEquals(Subtract.class, parser.parseExpr("1 - 1 / 1").getClass());
        assertEquals(Subtract.class, parser.parseExpr("1 * 1 - 1 / 1").getClass());
        assertEquals(Subtract.class, parser.parseExpr("1 - 1 - 1").getClass());
        assertNotEquals(parser.parseExpr("1 - 1 + 1").getClass(),Subtract.class);
    }
    
    @Test
    public void testMultiply() {
        assertEquals(Multiply.class, parser.parseExpr("1 * 1").getClass());
        assertEquals(Multiply.class, parser.parseExpr("-1 * -1").getClass());
        assertEquals(Multiply.class, parser.parseExpr("1 / 1 * 1").getClass());
        assertNotEquals(parser.parseExpr("1 * 1 / 1").getClass(),Multiply.class);
    }
    
    @Test
    public void testDivide() {
        assertEquals(Divide.class, parser.parseExpr("1 / 1").getClass());
        assertEquals(Divide.class, parser.parseExpr("-1 / -1").getClass());
        assertEquals(Divide.class, parser.parseExpr("1 * 1 / 1").getClass());
        assertNotEquals(parser.parseExpr("1 / 1 * 1").getClass(),Divide.class);
    }
    
    @Test
    public void testNot() {
        assertEquals(Negation.class, parser.parseExpr("!true").getClass());
        assertEquals(Negation.class, parser.parseExpr("!identifier").getClass());
        assertEquals(Negation.class, parser.parseExpr("!(A + B && C / c)").getClass());
    }
    
    @Test
    public void testAnd() {
        assertEquals(And.class, parser.parseExpr("true && false").getClass());
        assertEquals(And.class, parser.parseExpr("A && B").getClass());
        assertEquals(And.class, parser.parseExpr("A != B && C == c").getClass());
        assertEquals(And.class, parser.parseExpr("!A + B && C / c").getClass());
    }
    
    @Test
    public void testOr() {
        assertEquals(Or.class, parser.parseExpr("true || false").getClass());
        assertEquals(Or.class, parser.parseExpr("A || B").getClass());
        assertEquals(Or.class, parser.parseExpr("A != B && C == D || E").getClass());
        assertEquals(Or.class, parser.parseExpr("!A + B || C / c").getClass());
    }
    
    @Test
    public void testEqual() {
        assertEquals(Equal.class, parser.parseExpr("true == false").getClass());
        assertEquals(Equal.class, parser.parseExpr("A == B").getClass());
        assertEquals(Equal.class, parser.parseExpr("A + B == C / D").getClass());
    }
    
    @Test
    public void testNotEqual() {
        assertEquals(NotEqual.class, parser.parseExpr("true != false").getClass());
        assertEquals(NotEqual.class, parser.parseExpr("A != B").getClass());
        assertEquals(NotEqual.class, parser.parseExpr("A + B != C / D").getClass());
    }
    
    @Test
    public void testLess() {
        assertEquals(Less.class, parser.parseExpr("true < false").getClass());
        assertEquals(Less.class, parser.parseExpr("A < B").getClass());
        assertEquals(Less.class, parser.parseExpr("A + B < C / D").getClass());
    }
    
    @Test
    public void testLessEqual() {
        assertEquals(LessEqual.class, parser.parseExpr("true <= false").getClass());
        assertEquals(LessEqual.class, parser.parseExpr("A <= B").getClass());
        assertEquals(LessEqual.class, parser.parseExpr("A + B <= C / D").getClass());
    }
    
    @Test
    public void testGreater() {
        assertEquals(Greater.class, parser.parseExpr("true > false").getClass());
        assertEquals(Greater.class, parser.parseExpr("A > B").getClass());
        assertEquals(Greater.class, parser.parseExpr("A + B > C / D").getClass());
    }
    
    @Test
    public void testGreaterEqual() {
        assertEquals(GreaterEqual.class, parser.parseExpr("true >= false").getClass());
        assertEquals(GreaterEqual.class, parser.parseExpr("A >= B").getClass());
        assertEquals(GreaterEqual.class, parser.parseExpr("A + B >= C / D").getClass());
    }
}
