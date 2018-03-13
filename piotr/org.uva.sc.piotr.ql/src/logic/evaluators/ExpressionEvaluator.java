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

import java.util.HashMap;

public class ExpressionEvaluator extends AbstractASTTraverse<UniversalTypeValue> {

    private HashMap<String, UniversalTypeValue> variablesValues;

    public ExpressionEvaluator(HashMap<String, UniversalTypeValue> variablesValues) {
        this.variablesValues = variablesValues;
    }

    public void setVariablesValues(HashMap<String, UniversalTypeValue> variablesValues) {
        this.variablesValues = variablesValues;
    }

    @Override
    public UniversalTypeValue visit(Negation negation) {
        UniversalTypeValue result = negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public UniversalTypeValue visit(Minus minus) {
        UniversalTypeValue result = minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public UniversalTypeValue visit(Addition addition) {
        UniversalTypeValue lhs = addition.getLeftSide().accept(this);
        UniversalTypeValue rhs = addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public UniversalTypeValue visit(Subtraction subtraction) {
        UniversalTypeValue lhs = subtraction.getLeftSide().accept(this);
        UniversalTypeValue rhs = subtraction.getRightSide().accept(this);
        return lhs.subtract(rhs);
    }

    @Override
    public UniversalTypeValue visit(Division division) {
        UniversalTypeValue lhs = division.getLeftSide().accept(this);
        UniversalTypeValue rhs = division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public UniversalTypeValue visit(Multiplication multiplication) {
        UniversalTypeValue lhs = multiplication.getLeftSide().accept(this);
        UniversalTypeValue rhs = multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public UniversalTypeValue visit(Equal equal) {
        UniversalTypeValue lhs = equal.getLeftSide().accept(this);
        UniversalTypeValue rhs = equal.getRightSide().accept(this);
        return lhs.equals(rhs);
    }

    @Override
    public UniversalTypeValue visit(GreaterEqual greaterEqual) {
        UniversalTypeValue lhs = greaterEqual.getLeftSide().accept(this);
        UniversalTypeValue rhs = greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public UniversalTypeValue visit(GreaterThan greaterThan) {
        UniversalTypeValue lhs = greaterThan.getLeftSide().accept(this);
        UniversalTypeValue rhs = greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public UniversalTypeValue visit(LessEqual lessEqual) {
        UniversalTypeValue lhs = lessEqual.getLeftSide().accept(this);
        UniversalTypeValue rhs = lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public UniversalTypeValue visit(LessThan lessThan) {
        UniversalTypeValue lhs = lessThan.getLeftSide().accept(this);
        UniversalTypeValue rhs = lessThan.getRightSide().accept(this);
        return lhs.lessThan(rhs);
    }

    @Override
    public UniversalTypeValue visit(NotEqual notEqual) {
        UniversalTypeValue lhs = notEqual.getLeftSide().accept(this);
        UniversalTypeValue rhs = notEqual.getRightSide().accept(this);
        return lhs.notEquals(rhs);
    }

    @Override
    public UniversalTypeValue visit(LogicalAnd logicalAnd) {
        UniversalTypeValue lhs = logicalAnd.getLeftSide().accept(this);
        UniversalTypeValue rhs = logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public UniversalTypeValue visit(LogicalOr logicalOr) {
        UniversalTypeValue lhs = logicalOr.getLeftSide().accept(this);
        UniversalTypeValue rhs = logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public UniversalTypeValue visit(VariableReference variableReference) {
        return variablesValues.get(variableReference.getName());
    }

    @Override
    public UniversalTypeValue visit(Literal literal) {
        return UniversalTypeValue.createValue(literal.getType(), literal.getValue());
    }

}
