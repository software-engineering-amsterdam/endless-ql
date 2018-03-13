//import com.pholser.junit.quickcheck.Property;
//import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
//import model.expression.*;
//import model.expression.variable.ExpressionVariableBoolean;
//import model.expression.unary.ExpressionUnaryNeg;
//import model.expression.unary.ExpressionUnaryNot;
//import model.expression.variable.ExpressionVariableInteger;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(JUnitQuickcheck.class)
//public class ExpressionUnaryTest {
//
//    @Property
//    public void ExpressionUnaryNot(boolean value) {
//        Parser.ANTLRTester tester = new Parser.ANTLRTester("!" + value);
//        Expression actualExpression = tester.visitor.visit(tester.parser.model.expression());
//
//        ExpressionUnaryNot expectedExpression = new ExpressionUnaryNot(new ExpressionVariableBoolean(value));
//        assertEquals(expectedExpression, actualExpression);
//    }
//
//    @Property
//    public void ExpressionUnaryNegInteger(int value) {
//        Parser.ANTLRTester tester = new Parser.ANTLRTester("-" + value);
//        Expression actualExpression = tester.visitor.visit(tester.parser.model.expression());
//
//        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableInteger(value + ""));
//        assertEquals(expectedExpression, actualExpression);
//    }
//
//    @Property
//    public void ExpressionUnaryNegDecimal(double value) {
//        Parser.ANTLRTester tester = new Parser.ANTLRTester("-" + value);
//        Expression actualExpression = tester.visitor.visit(tester.parser.model.expression());
//
//        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableInteger(value + ""));
//        assertEquals(expectedExpression, actualExpression);
//    }
//}
