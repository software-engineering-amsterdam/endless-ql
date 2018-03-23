package qlviz.interpreter;

import com.google.inject.assistedinject.Assisted;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLBaseListener;
import qlviz.QLBaseVisitor;
import qlviz.QLLexer;
import qlviz.QLParser;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.numericExpressions.*;
import qlviz.model.question.NumericQuestionReference;

import static org.mockito.Mockito.mock;

public class NumericExpressionVisitorTest {

    private class OperatorTypeChecker implements NumericExpressionVisitor {

        private final BinaryNumericOperator operator;

        public OperatorTypeChecker(BinaryNumericOperator operator) {

            this.operator = operator;
        }

        @Override
        public void visit(BinaryNumericOperation binaryNumericOperation) {
            Assert.assertEquals(operator, binaryNumericOperation.getOperator());
        }

        @Override
        public void visit(NumericLiteral numericLiteral) {
            Assert.fail();
        }

        @Override
        public void visit(NumericNegation numericNegation) {
            Assert.fail();
        }

        @Override
        public void visit(NumericQuestionReference numericQuestionReference) {
            Assert.fail();
        }
    }

    private class BinaryExpressionDeconstructor implements
            TypedNumericExpressionVisitor<Pair<NumericExpression, NumericExpression>> {

        @Override
        public Pair<NumericExpression, NumericExpression> visit(BinaryNumericOperation binaryNumericOperation) {
           return Pair.of(binaryNumericOperation.getLeftSide(), binaryNumericOperation.getRightSide());
        }

        @Override
        public Pair<NumericExpression, NumericExpression> visit(NumericLiteral numericLiteral) {
            throw new IllegalArgumentException();
        }

        @Override
        public Pair<NumericExpression, NumericExpression> visit(NumericNegation numericNegation) {
            throw new IllegalArgumentException();
        }

        @Override
        public Pair<NumericExpression, NumericExpression> visit(NumericQuestionReference numericQuestionReference) {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void testPrecedence() {

        // Arrange
        var expressionVisitor = new NumericExpressionParser(new BinaryNumericOperatorVisitor());
        var input = "2+5*3";
        var lexer = new QLLexer(new ANTLRInputStream(input));
        var parser = new QLParser(new CommonTokenStream(lexer));

        // Act
        var expression = expressionVisitor.visitNumericExpression(parser.numericExpression());

        // Assert
        expression.accept(new OperatorTypeChecker(BinaryNumericOperator.Add));
    }
}
