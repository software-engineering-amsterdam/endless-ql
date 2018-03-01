import expression.*;
import expression.variable.*;
import expression.unary.ExpressionUnaryNeg;
import model.LookupTable;
import model.Question;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ExpressionVariableTest {

    @Test
    public void ExpressionBooleanTrueTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("true");
        Expression actualExpression = tester.visitor.visitBooleanConstant(tester.parser.booleanConstant());
        ExpressionVariableBoolean expectedExpression = new ExpressionVariableBoolean(true);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getBooleanValue(), actualExpression.evaluate().getBooleanValue());
    }

    @Test
    public void ExpressionBooleanFalseTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("false");
        Expression actualExpression = tester.visitor.visitBooleanConstant(tester.parser.booleanConstant());
        ExpressionVariableBoolean expectedExpression = new ExpressionVariableBoolean(false);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getBooleanValue(), actualExpression.evaluate().getBooleanValue());
    }

    @Test
    public void ExpressionIntegerPositiveTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("1");
        Expression actualExpression = tester.visitor.visitIntegerConstant(tester.parser.integerConstant());
        ExpressionVariableNumber expectedExpression = new ExpressionVariableNumber(1);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getIntValue(), actualExpression.evaluate().getIntValue());
    }

    @Test
    public void ExpressionIntegerNegativeTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("-1");
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableNumber(1));

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getIntValue(), actualExpression.evaluate().getIntValue());
    }

    @Test
    public void ExpressionDecimalPositiveTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("1.0");
        Expression actualExpression = tester.visitor.visitDecimalConstant(tester.parser.decimalConstant());
        ExpressionVariableNumber expectedExpression = new ExpressionVariableNumber(1.0);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getDecimalValue(), actualExpression.evaluate().getDecimalValue());
    }

    @Test
    public void ExpressionDecimalNegativeTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("-1.0");
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableNumber(1.0));

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getDecimalValue(), actualExpression.evaluate().getDecimalValue());
    }

//    @Test
//    public void ExpressionDateTest() throws IOException {
//        ANTLRTester tester = new ANTLRTester("1-1-2018");
//        Expression actualExpression = tester.visitor.visitDateConstant(tester.parser.dateConstant());
//        ExpressionVariableDate expectedExpression = new ExpressionVariableDate("1-1-2018");
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate().getValue(), actualExpression.evaluate().getValue());
//    }

    @Test
    public void ExpressionMoneyTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("1.99");
        Expression actualExpression = tester.visitor.visitMoneyConstant(tester.parser.moneyConstant());
        ExpressionVariableNumber expectedExpression = new ExpressionVariableNumber(BigDecimal.valueOf(1.99));

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getMoneyValue(), actualExpression.evaluate().getMoneyValue());
    }

    @Test
    public void ExpressionStringTest() throws IOException {
        ANTLRTester tester = new ANTLRTester("\"Could you give me some value?\"");
        Expression actualExpression = tester.visitor.visitStringConstant(tester.parser.stringConstant());
        ExpressionVariableString expectedExpression = new ExpressionVariableString("Could you give me some value?");

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().getStringValue(), actualExpression.evaluate().getStringValue());
    }

//    @Test
//    public void ExpressionIdentifierTest() throws IOException {
//        // Add dummy question to lookuptable so type of identifier can be inferred
//        Question dummyQuestion = new Question(ReturnType.INTEGER,"someQuestionIdentifier_123", "",
//                new ExpressionVariableNumber(1), new ExpressionVariableBoolean(true));
//        LookupTable.getInstance().insert(dummyQuestion);
//
//        ANTLRTester tester = new ANTLRTester("someQuestionIdentifier_123");
//        Expression actualExpression = tester.visitor.visitIdentifierConstant(tester.parser.identifierConstant());
//        ExpressionIdentifier<Integer> expectedExpression = new ExpressionIdentifier<Integer>("someQuestionIdentifier_123");
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate().getValue(), actualExpression.evaluate().getValue());
//    }




    // TODO move to form test class
//
//    @Test
//    public void FormWithAllTypesOfExpressionsTest() throws IOException {
//        // Actual
//        String fileName = "test/variables.ql";
//        Form actualForm = parseForm(fileName);
//
//        // Expected
//        ArrayList<Statement> statements = new ArrayList<>() {{
//            add(new Question("q1", "Can you give me a boolean?", new ExpressionVariableBoolean(true)));
//            add(new Question("q2", "Can you give me a string?", new ExpressionVariableString("hello")));
//            add(new Question("q3", "Can you give me a integer?", new ExpressionVariableNumber(1)));
//            add(new Question("q4", "Can you give me a date?", new ExpressionVariableDate("1-1-2018")));
//            add(new Question("q5", "Can you give me a decimal?", new ExpressionVariableNumber(1.2345)));
//            add(new Question("q6", "Can you give me a money?", new ExpressionVariableNumber(BigDecimal.valueOf(1.99))));
//        }};
//        Form expectedForm = new Form("testForm", statements);
//        assertEquals(expectedForm.toString().trim(), actualForm.toString().trim());
//    }
//
//    // TODO: test conditions, conditions within conditions, identifiers, etc


}


