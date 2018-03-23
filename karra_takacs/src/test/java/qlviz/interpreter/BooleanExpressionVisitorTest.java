package qlviz.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLLexer;
import qlviz.QLParser;
import qlviz.QLVisitor;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.booleanExpressions.*;
import qlviz.model.question.BooleanQuestionReference;

import static org.mockito.Mockito.mock;

public class BooleanExpressionVisitorTest {

    private abstract class BaseBooleanExpressionVisitor implements BooleanExpressionVisitor {
        @Override
        public void visit(BinaryBooleanOperation binaryBooleanOperation) {
            Assert.fail();
        }

        @Override
        public void visit(BooleanLiteral literal) {
            Assert.fail();
        }

        @Override
        public void visit(Negation negation) {
            Assert.fail();
        }

        @Override
        public void visit(BooleanQuestionReference booleanQuestionReference) {
            Assert.fail();
        }

        @Override
        public void visit(NumericComparison numericComparison) {
            Assert.fail();
        }
    }

    private abstract class BaseTypedBooleanExpressionVisitor<T> implements TypedBooleanExpressionVisitor<T> {
        @Override
        public T visit(BinaryBooleanOperation binaryBooleanOperation) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(BooleanLiteral literal) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(Negation negation) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(BooleanQuestionReference booleanQuestionReference) {
            throw new IllegalArgumentException();
        }

        @Override
        public T visit(NumericComparison numericComparison) {
            throw new IllegalArgumentException();
        }
    }

    private class BinaryBooleanExpressionDeconstructor extends BaseTypedBooleanExpressionVisitor<Pair<BooleanExpression, BooleanExpression>> {
        @Override
        public Pair<BooleanExpression, BooleanExpression> visit(BinaryBooleanOperation binaryBooleanOperation) {
            return Pair.of(binaryBooleanOperation.getLeftSide(), binaryBooleanOperation.getRightSide());
        }
    }

    @Test
    public void testPrecedence() {
        // Arrange
        var input = "false || true && true";
        var numericVisitorMock = mock(QLVisitor.class);
        var visitor = new BooleanExpressionParser(
                numericVisitorMock,
                new BinaryBooleanOperatorVisitor(),
                new NumericComparisonOperatorVisitor()
        );
        var lexer = new QLLexer(new ANTLRInputStream(input));
        var parser = new QLParser(new CommonTokenStream(lexer));

        // Act
        var expression = visitor.visitBooleanExpression(parser.booleanExpression());

        // Assert
        expression.accept(new BaseBooleanExpressionVisitor(){
            @Override
            public void visit(BinaryBooleanOperation binaryBooleanOperation) {
                Assert.assertEquals(BinaryBooleanOperator.Or, binaryBooleanOperation.getOperator());
            }
        });
        var operands = expression.accept(new BinaryBooleanExpressionDeconstructor());
        operands.getLeft().accept(new BaseBooleanExpressionVisitor() {
            @Override
            public void visit(BooleanLiteral literal) {
                Assert.assertEquals(false, literal.getValue());
            }
        });
        operands.getRight().accept(new BaseBooleanExpressionVisitor() {
            @Override
            public void visit(BinaryBooleanOperation binaryBooleanOperation) {
                Assert.assertEquals(BinaryBooleanOperator.And, binaryBooleanOperation.getOperator());
            }
        });
        operands = operands.getRight().accept(new BinaryBooleanExpressionDeconstructor());
        operands.getRight().accept(new BaseBooleanExpressionVisitor() {
            @Override
            public void visit(BooleanLiteral literal) {
                Assert.assertEquals(true, literal.getValue());
            }
        });
        operands.getLeft().accept(new BaseBooleanExpressionVisitor() {
            @Override
            public void visit(BooleanLiteral literal) {
                Assert.assertEquals(true, literal.getValue());
            }
        });
    }
}
