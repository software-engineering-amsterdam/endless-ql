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
import logic.type.MixedValue;

import java.util.HashMap;

public class ExpressionEvaluator extends AbstractASTTraverse<MixedValue> {

    private final HashMap<String, MixedValue> variablesValues;

    public ExpressionEvaluator(HashMap<String, MixedValue> variablesValues) {
        this.variablesValues = variablesValues;
    }

    @Override
    public MixedValue visit(Negation negation) {
        MixedValue result = negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public MixedValue visit(Minus minus) {
        MixedValue result = minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public MixedValue visit(Addition addition) {
        MixedValue lhs = addition.getLeftSide().accept(this);
        MixedValue rhs = addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public MixedValue visit(Subtraction subtraction) {
        MixedValue lhs = subtraction.getLeftSide().accept(this);
        MixedValue rhs = subtraction.getRightSide().accept(this);
        return lhs.subtract(rhs);
    }

    @Override
    public MixedValue visit(Division division) {
        MixedValue lhs = division.getLeftSide().accept(this);
        MixedValue rhs = division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public MixedValue visit(Multiplication multiplication) {
        MixedValue lhs = multiplication.getLeftSide().accept(this);
        MixedValue rhs = multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public MixedValue visit(Equal equal) {
        MixedValue lhs = equal.getLeftSide().accept(this);
        MixedValue rhs = equal.getRightSide().accept(this);
        return lhs.equals(rhs);
    }

    @Override
    public MixedValue visit(GreaterEqual greaterEqual) {
        MixedValue lhs = greaterEqual.getLeftSide().accept(this);
        MixedValue rhs = greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public MixedValue visit(GreaterThan greaterThan) {
        MixedValue lhs = greaterThan.getLeftSide().accept(this);
        MixedValue rhs = greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public MixedValue visit(LessEqual lessEqual) {
        MixedValue lhs = lessEqual.getLeftSide().accept(this);
        MixedValue rhs = lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public MixedValue visit(LessThan lessThan) {
        MixedValue lhs = lessThan.getLeftSide().accept(this);
        MixedValue rhs = lessThan.getRightSide().accept(this);
        return lhs.lessThan(rhs);
    }

    @Override
    public MixedValue visit(NotEqual notEqual) {
        MixedValue lhs = notEqual.getLeftSide().accept(this);
        MixedValue rhs = notEqual.getRightSide().accept(this);
        return lhs.notEquals(rhs);
    }

    @Override
    public MixedValue visit(LogicalAnd logicalAnd) {
        MixedValue lhs = logicalAnd.getLeftSide().accept(this);
        MixedValue rhs = logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public MixedValue visit(LogicalOr logicalOr) {
        MixedValue lhs = logicalOr.getLeftSide().accept(this);
        MixedValue rhs = logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public MixedValue visit(VariableReference variableReference) {
        return variablesValues.get(variableReference.getName());
    }

    @Override
    public MixedValue visit(Literal literal) {
        return MixedValue.createValue(literal.getType(), literal.getValue());
    }

}
