package ql.logic.evaluators;

import ql.ast.model.expressions.binary.arithmetics.Addition;
import ql.ast.model.expressions.binary.arithmetics.Division;
import ql.ast.model.expressions.binary.arithmetics.Multiplication;
import ql.ast.model.expressions.binary.arithmetics.Subtraction;
import ql.ast.model.expressions.binary.comparision.*;
import ql.ast.model.expressions.binary.logical.LogicalAnd;
import ql.ast.model.expressions.binary.logical.LogicalOr;
import ql.ast.model.expressions.unary.arithmetics.Minus;
import ql.ast.model.expressions.unary.logical.Negation;
import ql.ast.model.expressions.values.Literal;
import ql.ast.visitors.AbstractASTTraverse;
import ql.logic.type.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class AbstractExpressionEvaluator extends AbstractASTTraverse<QLDataType> {

    @Override
    public QLDataTypeBoolean visit(Negation negation) {
        QLDataTypeBoolean result = (QLDataTypeBoolean) negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public QLDataTypeNumeric visit(Minus minus) {
        QLDataTypeNumeric result = (QLDataTypeNumeric) minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public QLDataTypeSummable visit(Addition addition) {
        QLDataTypeSummable lhs = (QLDataTypeSummable) addition.getLeftSide().accept(this);
        QLDataTypeSummable rhs = (QLDataTypeSummable) addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public QLDataTypeNumeric visit(Subtraction subtraction) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) subtraction.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) subtraction.getRightSide().accept(this);
        return lhs.subtract(rhs);
    }

    @Override
    public QLDataTypeNumeric visit(Division division) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) division.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public QLDataTypeNumeric visit(Multiplication multiplication) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) multiplication.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public QLDataType visit(Equal equal) {
        QLDataType lhs = equal.getLeftSide().accept(this);
        QLDataType rhs = equal.getRightSide().accept(this);
        return lhs.equals(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(GreaterEqual greaterEqual) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) greaterEqual.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(GreaterThan greaterThan) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) greaterThan.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(LessEqual lessEqual) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) lessEqual.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(LessThan lessThan) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) lessThan.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) lessThan.getRightSide().accept(this);
        return lhs.lessThan(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(NotEqual notEqual) {
        QLDataTypeNumeric lhs = (QLDataTypeNumeric) notEqual.getLeftSide().accept(this);
        QLDataTypeNumeric rhs = (QLDataTypeNumeric) notEqual.getRightSide().accept(this);
        return lhs.notEquals(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(LogicalAnd logicalAnd) {
        QLDataTypeBoolean lhs = (QLDataTypeBoolean) logicalAnd.getLeftSide().accept(this);
        QLDataTypeBoolean rhs = (QLDataTypeBoolean) logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public QLDataTypeBoolean visit(LogicalOr logicalOr) {
        QLDataTypeBoolean lhs = (QLDataTypeBoolean) logicalOr.getLeftSide().accept(this);
        QLDataTypeBoolean rhs = (QLDataTypeBoolean) logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public QLDataType visit(Literal literal) {
        return QLDataType.createValue(literal.getType(), literal.getValue());
    }

}
