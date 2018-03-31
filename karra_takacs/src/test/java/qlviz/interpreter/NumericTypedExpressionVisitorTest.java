package qlviz.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLLexer;
import qlviz.QLParser;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.expressions.numericExpressions.*;
import qlviz.model.question.NumericQuestionReference;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

public class NumericTypedExpressionVisitorTest {

    private abstract class BaseNumericExpressionVisitor implements NumericExpressionVisitor {
        @Override
        public void visit(BinaryNumericOperation binaryNumericOperation) {
            Assert.fail();
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

    private abstract class BaseTypedNumericExpressionVisitor<T> implements TypedNumericExpressionVisitor<T> {
        @Override
        public T visit(BinaryNumericOperation binaryNumericOperation) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(NumericLiteral numericLiteral) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(NumericNegation numericNegation) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(NumericQuestionReference numericQuestionReference) {
            throw new IllegalArgumentException();
        }
    }

    private class OperatorTypeChecker extends BaseNumericExpressionVisitor {

        private final BinaryNumericOperator operator;

        public OperatorTypeChecker(BinaryNumericOperator operator) {

            this.operator = operator;
        }

        @Override
        public void visit(BinaryNumericOperation binaryNumericOperation) {
            Assert.assertEquals(operator, binaryNumericOperation.getOperator());
        }
    }

    private class BinaryExpressionDeconstructor extends
            BaseTypedNumericExpressionVisitor<Pair<NumericExpression, NumericExpression>> {

        @Override
        public Pair<NumericExpression, NumericExpression> visit(BinaryNumericOperation binaryNumericOperation) {
           return Pair.of(binaryNumericOperation.getLeftSide(), binaryNumericOperation.getRightSide());
        }
    }

    private class NumericLiteralChecker extends BaseNumericExpressionVisitor {

        private final BigDecimal number;

        public NumericLiteralChecker(BigDecimal number) {
            this.number = number;
        }

        @Override
        public void visit(NumericLiteral numericLiteral) {
            Assert.assertEquals(number, numericLiteral.getValue());
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
        var operands = expression.accept(new BinaryExpressionDeconstructor());
        operands.getLeft().accept(new NumericLiteralChecker(BigDecimal.valueOf(2)));
        operands.getRight().accept(new OperatorTypeChecker(BinaryNumericOperator.Multiply));
        operands = operands.getRight().accept(new BinaryExpressionDeconstructor());
        operands.getLeft().accept(new NumericLiteralChecker(BigDecimal.valueOf(5)));
        operands.getRight().accept(new NumericLiteralChecker(BigDecimal.valueOf(3)));
    }

    @Test
    public void testParenthesisPrecedence() {
        // Arrange
        var expressionVisitor = new NumericExpressionParser(new BinaryNumericOperatorVisitor());
        var input = "(2+5)*3";
        var lexer = new QLLexer(new ANTLRInputStream(input));
        var parser = new QLParser(new CommonTokenStream(lexer));

        // Act
        var expression = expressionVisitor.visitNumericExpression(parser.numericExpression());

        // Assert
        expression.accept(new OperatorTypeChecker(BinaryNumericOperator.Multiply));
        var operands = expression.accept(new BinaryExpressionDeconstructor());
        operands.getRight().accept(new NumericLiteralChecker(BigDecimal.valueOf(3)));
        operands.getLeft().accept(new OperatorTypeChecker(BinaryNumericOperator.Add));
        operands = operands.getLeft().accept(new BinaryExpressionDeconstructor());
        operands.getLeft().accept(new NumericLiteralChecker(BigDecimal.valueOf(2)));
        operands.getRight().accept(new NumericLiteralChecker(BigDecimal.valueOf(5)));

    }

    @Test
    public void testUnaryOperator() {
        // Arrange
        var expressionVisitor = new NumericExpressionParser(new BinaryNumericOperatorVisitor());
        var input = "-5*3";
        var lexer = new QLLexer(new ANTLRInputStream(input));
        var parser = new QLParser(new CommonTokenStream(lexer));

        // Act
        var expression = expressionVisitor.visitNumericExpression(parser.numericExpression());
        expression.accept(new OperatorTypeChecker(BinaryNumericOperator.Multiply));
        var operands = expression.accept(new BinaryExpressionDeconstructor());
        operands.getRight().accept(new NumericLiteralChecker(BigDecimal.valueOf(3)));
        operands.getLeft().accept(new BaseNumericExpressionVisitor() {
            @Override
            public void visit(NumericNegation numericNegation) {
                numericNegation.getInnerExpression().accept(new NumericLiteralChecker(BigDecimal.valueOf(5)));
            }
        });
    }
}
