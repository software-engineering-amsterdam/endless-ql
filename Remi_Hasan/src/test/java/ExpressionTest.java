import antlr.QLLexer;
import antlr.QLParser;
import expression.*;
import jdk.dynalink.linker.support.Lookup;
import model.LookupTable;
import model.Question;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import visitor.VisitorExpression;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {

    @Test
    public void ExpressionBooleanTrueTest() throws IOException {
        AntlrTester tester = new AntlrTester("true");
        Expression actualExpression = tester.visitor.visitBooleanConstant(tester.parser.booleanConstant());
        ExpressionVariableBoolean expectedExpression = new ExpressionVariableBoolean(true);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionBooleanFalseTest() throws IOException {
        AntlrTester tester = new AntlrTester("false");
        Expression actualExpression = tester.visitor.visitBooleanConstant(tester.parser.booleanConstant());
        ExpressionVariableBoolean expectedExpression = new ExpressionVariableBoolean(false);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    // TODO remove this
//    booleanConstant : (TRUE | FALSE);
//    integerConstant : INTEGER;
//    decimalConstant : DECIMAL;
//    dateConstant : DATE;
//    moneyConstant : MONEY;
//    stringConstant : STRING;
//    identifierConstant : IDENTIFIER;

    @Test
    public void ExpressionIntegerPositiveTest() throws IOException {
        AntlrTester tester = new AntlrTester("1");
        Expression actualExpression = tester.visitor.visitIntegerConstant(tester.parser.integerConstant());
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(1);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionIntegerNegativeTest() throws IOException {
        AntlrTester tester = new AntlrTester("-1");
        Expression actualExpression = tester.visitor.visitIntegerConstant(tester.parser.integerConstant());
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(-1);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionDecimalPositiveTest() throws IOException {
        AntlrTester tester = new AntlrTester("1.0");
        Expression actualExpression = tester.visitor.visitDecimalConstant(tester.parser.decimalConstant());
        ExpressionVariableDecimal expectedExpression = new ExpressionVariableDecimal(1.0);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionDecimalNegativeTest() throws IOException {
        AntlrTester tester = new AntlrTester("-1.0");
        Expression actualExpression = tester.visitor.visitDecimalConstant(tester.parser.decimalConstant());
        ExpressionVariableDecimal expectedExpression = new ExpressionVariableDecimal(-1.0);

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionDateTest() throws IOException {
        AntlrTester tester = new AntlrTester("1-1-2018");
        Expression actualExpression = tester.visitor.visitDateConstant(tester.parser.dateConstant());
        ExpressionVariableDate expectedExpression = new ExpressionVariableDate("1-1-2018");

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionMoneyTest() throws IOException {
        AntlrTester tester = new AntlrTester("1.99");
        Expression actualExpression = tester.visitor.visitMoneyConstant(tester.parser.moneyConstant());
        ExpressionVariableMoney expectedExpression = new ExpressionVariableMoney(BigDecimal.valueOf(1.99));

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionStringTest() throws IOException {
        AntlrTester tester = new AntlrTester("\"Could you give me some value?\"");
        Expression actualExpression = tester.visitor.visitStringConstant(tester.parser.stringConstant());
        ExpressionVariableString expectedExpression = new ExpressionVariableString("Could you give me some value?");

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

    @Test
    public void ExpressionIdentifierTest() throws IOException {
        // Add dummy question to lookuptable so type of identifier can be inferred
        Question dummyQuestion = new Question("someQuestionIdentifier_123", "", new ExpressionVariableInteger(1));
        LookupTable.getInstance().insert(dummyQuestion);

        AntlrTester tester = new AntlrTester("someQuestionIdentifier_123");
        Expression actualExpression = tester.visitor.visitIdentifierConstant(tester.parser.identifierConstant());
        ExpressionIdentifier<Integer> expectedExpression = new ExpressionIdentifier<Integer>("someQuestionIdentifier_123");

        // TODO implement real equals
        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }

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
//            add(new Question("q3", "Can you give me a integer?", new ExpressionVariableInteger(1)));
//            add(new Question("q4", "Can you give me a date?", new ExpressionVariableDate("1-1-2018")));
//            add(new Question("q5", "Can you give me a decimal?", new ExpressionVariableDecimal(1.2345)));
//            add(new Question("q6", "Can you give me a money?", new ExpressionVariableMoney(BigDecimal.valueOf(1.99))));
//        }};
//        Form expectedForm = new Form("testForm", statements);
//        assertEquals(expectedForm.toString().trim(), actualForm.toString().trim());
//    }
//
//    // TODO: test conditions, conditions within conditions, identifiers, etc


}


class AntlrTester{

    final QLParser parser;
    final VisitorExpression visitor;

    AntlrTester(String input){
        //        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ANTLRInputStream stream = new ANTLRInputStream(input);
        QLLexer lexer = new QLLexer(stream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        this.parser = new QLParser(tokens);

        // Walk it and attach our listener
        this.visitor = new VisitorExpression();
    }
}