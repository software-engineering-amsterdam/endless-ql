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
import ql.logic.type.QLBoolean;
import ql.logic.type.QLDataTypeWrapper;
import ql.logic.type.QLDataTypeWrapper;
import ql.logic.type.QLDataTypeWrapper;

public abstract class AbstractExpressionEvaluator extends AbstractASTTraverse<QLDataTypeWrapper> {

    @Override
    public QLBoolean visit(Negation negation) {
        QLBoolean result = (QLBoolean) negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public QLDataTypeWrapper visit(Minus minus) {
        QLDataTypeWrapper result = minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public QLDataTypeWrapper visit(Addition addition) {
        QLDataTypeWrapper lhs = addition.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public QLDataTypeWrapper visit(Subtraction subtraction) {
        QLDataTypeWrapper lhs = subtraction.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = subtraction.getRightSide().accept(this);
        return lhs.subtract(rhs);
    }

    @Override
    public QLDataTypeWrapper visit(Division division) {
        QLDataTypeWrapper lhs = division.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public QLDataTypeWrapper visit(Multiplication multiplication) {
        QLDataTypeWrapper lhs = multiplication.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public QLDataTypeWrapper visit(Equal equal) {
        QLDataTypeWrapper lhs = equal.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = equal.getRightSide().accept(this);
        return lhs.equals(rhs);
    }

    @Override
    public QLBoolean visit(GreaterEqual greaterEqual) {
        QLDataTypeWrapper lhs = greaterEqual.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public QLBoolean visit(GreaterThan greaterThan) {
        QLDataTypeWrapper lhs = greaterThan.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public QLBoolean visit(LessEqual lessEqual) {
        QLDataTypeWrapper lhs = lessEqual.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public QLBoolean visit(LessThan lessThan) {
        QLDataTypeWrapper lhs = lessThan.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = lessThan.getRightSide().accept(this);
        return lhs.lessThan(rhs);
    }

    @Override
    public QLBoolean visit(NotEqual notEqual) {
        QLDataTypeWrapper lhs = notEqual.getLeftSide().accept(this);
        QLDataTypeWrapper rhs = notEqual.getRightSide().accept(this);
        return lhs.notEquals(rhs);
    }

    @Override
    public QLBoolean visit(LogicalAnd logicalAnd) {
        QLBoolean lhs = (QLBoolean) logicalAnd.getLeftSide().accept(this);
        QLBoolean rhs = (QLBoolean) logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public QLBoolean visit(LogicalOr logicalOr) {
        QLBoolean lhs = (QLBoolean) logicalOr.getLeftSide().accept(this);
        QLBoolean rhs = (QLBoolean) logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public QLDataTypeWrapper visit(Literal literal) {
        return QLDataTypeWrapper.createValue(literal.getType(), literal.getValue());
    }

}
