package logic.evaluators;

import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.values.Literal;
import ast.model.expressions.values.VariableReference;
import ast.visitors.AbstractASTTraverse;
import gui.model.MixedValueHolder;

import java.util.HashMap;

public class ExpressionEvaluator extends AbstractASTTraverse<MixedValueHolder> {

    private HashMap<String, MixedValueHolder> variablesValues;

    public ExpressionEvaluator(HashMap<String, MixedValueHolder> variablesValues) {
        this.variablesValues = variablesValues;
    }

    @Override
    public MixedValueHolder visit(Negation negation) {
        MixedValueHolder result = negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public MixedValueHolder visit(Minus minus) {
        MixedValueHolder result = minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public MixedValueHolder visit(Addition addition) {
        MixedValueHolder lhs = addition.getLeftSide().accept(this);
        MixedValueHolder rhs = addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public MixedValueHolder visit(Subtraction subtraction) {
        MixedValueHolder lhs = subtraction.getLeftSide().accept(this);
        MixedValueHolder rhs = subtraction.getRightSide().accept(this);
        return lhs.subtract(rhs);
    }

    @Override
    public MixedValueHolder visit(Division division) {
        MixedValueHolder lhs = division.getLeftSide().accept(this);
        MixedValueHolder rhs = division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public MixedValueHolder visit(Multiplication multiplication) {
        MixedValueHolder lhs = multiplication.getLeftSide().accept(this);
        MixedValueHolder rhs = multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public MixedValueHolder visit(Equal equal) {
        MixedValueHolder lhs = equal.getLeftSide().accept(this);
        MixedValueHolder rhs = equal.getRightSide().accept(this);
        return lhs.equals(rhs);
    }

    @Override
    public MixedValueHolder visit(GreaterEqual greaterEqual) {
        MixedValueHolder lhs = greaterEqual.getLeftSide().accept(this);
        MixedValueHolder rhs = greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public MixedValueHolder visit(GreaterThan greaterThan) {
        MixedValueHolder lhs = greaterThan.getLeftSide().accept(this);
        MixedValueHolder rhs = greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public MixedValueHolder visit(LessEqual lessEqual) {
        MixedValueHolder lhs = lessEqual.getLeftSide().accept(this);
        MixedValueHolder rhs = lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public MixedValueHolder visit(LessThan lessThan) {
        MixedValueHolder lhs = lessThan.getLeftSide().accept(this);
        MixedValueHolder rhs = lessThan.getRightSide().accept(this);
        return lhs.lessThan(rhs);
    }

    @Override
    public MixedValueHolder visit(NotEqual notEqual) {
        MixedValueHolder lhs = notEqual.getLeftSide().accept(this);
        MixedValueHolder rhs = notEqual.getRightSide().accept(this);
        return lhs.notEquals(rhs);
    }

    @Override
    public MixedValueHolder visit(LogicalAnd logicalAnd) {
        MixedValueHolder lhs = logicalAnd.getLeftSide().accept(this);
        MixedValueHolder rhs = logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public MixedValueHolder visit(LogicalOr logicalOr) {
        MixedValueHolder lhs = logicalOr.getLeftSide().accept(this);
        MixedValueHolder rhs = logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public MixedValueHolder visit(VariableReference variableReference) {
        return variablesValues.get(variableReference.getName());
    }

    @Override
    public MixedValueHolder visit(Literal literal) {
        return MixedValueHolder.createValueHolder(literal.getType(), literal.getValue());
    }

}
