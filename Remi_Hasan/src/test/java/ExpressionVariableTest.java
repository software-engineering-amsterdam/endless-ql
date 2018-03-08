//import analysis.SymbolTable;
//import model.expression.*;
//import model.expression.variable.*;
//import model.expression.unary.ExpressionUnaryNeg;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.assertEquals;
//
//public class ExpressionVariableTest {
//
//    private SymbolTable symbolTable = new SymbolTable();
//
//    @Test
//    public void ExpressionBooleanTrueTest() {
//        ANTLRTester tester = new ANTLRTester("true");
//        Expression actualExpression = tester.visitor.visitBooleanConstant(tester.parser.booleanConstant());
//        ExpressionVariableBoolean expectedExpression = new ExpressionVariableBoolean(true);
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getBooleanValue(),
//                actualExpression.evaluate(symbolTable).getBooleanValue());
//    }
//
//    @Test
//    public void ExpressionBooleanFalseTest() {
//        ANTLRTester tester = new ANTLRTester("false");
//        Expression actualExpression = tester.visitor.visitBooleanConstant(tester.parser.booleanConstant());
//        ExpressionVariableBoolean expectedExpression = new ExpressionVariableBoolean(false);
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getBooleanValue(),
//                actualExpression.evaluate(symbolTable).getBooleanValue());
//    }
//
//    @Test
//    public void ExpressionIntegerPositiveTest() {
//        ANTLRTester tester = new ANTLRTester("1");
//        Expression actualExpression = tester.visitor.visitIntegerConstant(tester.parser.integerConstant());
//        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(1);
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getIntValue(),
//                actualExpression.evaluate(symbolTable).getIntValue());
//    }
//
//    @Test
//    public void ExpressionIntegerNegativeTest() {
//        ANTLRTester tester = new ANTLRTester("-1");
//        Expression actualExpression = tester.visitor.visit(tester.parser.model.expression());
//        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableInteger(1));
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getIntValue(),
//                actualExpression.evaluate(symbolTable).getIntValue());
//    }
//
//    @Test
//    public void ExpressionDecimalPositiveTest() {
//        ANTLRTester tester = new ANTLRTester("1.0");
//        Expression actualExpression = tester.visitor.visitDecimalConstant(tester.parser.decimalConstant());
//        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(1.0);
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getDecimalValue(),
//                actualExpression.evaluate(symbolTable).getDecimalValue());
//    }
//
//    @Test
//    public void ExpressionDecimalNegativeTest() {
//        ANTLRTester tester = new ANTLRTester("-1.0");
//        Expression actualExpression = tester.visitor.visit(tester.parser.model.expression());
//        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableInteger(1.0));
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getDecimalValue(),
//                actualExpression.evaluate(symbolTable).getDecimalValue());
//    }
//
//    @Test
//    public void ExpressionMoneyTest() {
//        ANTLRTester tester = new ANTLRTester("1.99");
//        Expression actualExpression = tester.visitor.visitMoneyConstant(tester.parser.moneyConstant());
//        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(BigDecimal.valueOf(1.99));
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getMoneyValue(),
//                actualExpression.evaluate(symbolTable).getMoneyValue());
//    }
//
//    @Test
//    public void ExpressionStringTest() {
//        ANTLRTester tester = new ANTLRTester("\"Could you give me some value?\"");
//        Expression actualExpression = tester.visitor.visitStringConstant(tester.parser.stringConstant());
//        ExpressionVariableString expectedExpression = new ExpressionVariableString("Could you give me some value?");
//
//        // TODO implement real equals
//        assertEquals(expectedExpression.evaluate(symbolTable).getStringValue(),
//                actualExpression.evaluate(symbolTable).getStringValue());
//    }
//
//    // TODO: test date and variables in lookup table
//
//
//}
//
//
