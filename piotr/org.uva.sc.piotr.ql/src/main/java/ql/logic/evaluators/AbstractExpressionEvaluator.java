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
import ql.logic.type.QLNumeric;
import ql.logic.type.QLSummable;

public abstract class AbstractExpressionEvaluator extends AbstractASTTraverse<QLDataTypeWrapper> {

    @Override
    public QLBoolean visit(Negation negation) {
        QLBoolean result = (QLBoolean) negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public QLNumeric visit(Minus minus) {
        QLNumeric result = (QLNumeric) minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public QLSummable visit(Addition addition) {
        QLSummable lhs = (QLSummable) addition.getLeftSide().accept(this);
        QLSummable rhs = (QLSummable) addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public QLNumeric visit(Subtraction subtraction) {
        QLNumeric lhs = (QLNumeric) subtraction.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) subtraction.getRightSide().accept(this);
        return lhs.subtract(rhs);
    }

    @Override
    public QLNumeric visit(Division division) {
        QLNumeric lhs = (QLNumeric) division.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public QLNumeric visit(Multiplication multiplication) {
        QLNumeric lhs = (QLNumeric) multiplication.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) multiplication.getRightSide().accept(this);
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
        QLNumeric lhs = (QLNumeric) greaterEqual.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public QLBoolean visit(GreaterThan greaterThan) {
        QLNumeric lhs = (QLNumeric) greaterThan.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public QLBoolean visit(LessEqual lessEqual) {
        QLNumeric lhs = (QLNumeric) lessEqual.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public QLBoolean visit(LessThan lessThan) {
        QLNumeric lhs = (QLNumeric) lessThan.getLeftSide().accept(this);
        QLNumeric rhs = (QLNumeric) lessThan.getRightSide().accept(this);
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
