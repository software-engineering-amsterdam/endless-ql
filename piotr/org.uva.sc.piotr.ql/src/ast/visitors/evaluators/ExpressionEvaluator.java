package ast.visitors.evaluators;

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

public class ExpressionEvaluator extends AbstractASTTraverse<ExpressionResult> {

    private HashMap<String, ExpressionResult> variablesValues;

    public ExpressionEvaluator(HashMap<String, ExpressionResult> variablesValues) {
        this.variablesValues = variablesValues;
    }

    public void setVariablesValues(HashMap<String, ExpressionResult> variablesValues) {
        this.variablesValues = variablesValues;
    }

    @Override
    public ExpressionResult visit(Negation negation) {
        ExpressionResult result = negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public ExpressionResult visit(Minus minus) {
        ExpressionResult result = minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public ExpressionResult visit(Addition addition) {
        ExpressionResult lhs = addition.getLeftSide().accept(this);
        ExpressionResult rhs = addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public ExpressionResult visit(Subtraction subtraction) {
        ExpressionResult lhs = subtraction.getLeftSide().accept(this);
        ExpressionResult rhs = subtraction.getRightSide().accept(this);
        return lhs.min(rhs);
    }

    @Override
    public ExpressionResult visit(Division division) {
        ExpressionResult lhs = division.getLeftSide().accept(this);
        ExpressionResult rhs = division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public ExpressionResult visit(Multiplication multiplication) {
        ExpressionResult lhs = multiplication.getLeftSide().accept(this);
        ExpressionResult rhs = multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public ExpressionResult visit(Equal equal) {
        ExpressionResult lhs = equal.getLeftSide().accept(this);
        ExpressionResult rhs = equal.getRightSide().accept(this);
        return lhs.equals(rhs);
    }

    @Override
    public ExpressionResult visit(GreaterEqual greaterEqual) {
        ExpressionResult lhs = greaterEqual.getLeftSide().accept(this);
        ExpressionResult rhs = greaterEqual.getRightSide().accept(this);
        return lhs.greaterEqual(rhs);
    }

    @Override
    public ExpressionResult visit(GreaterThan greaterThan) {
        ExpressionResult lhs = greaterThan.getLeftSide().accept(this);
        ExpressionResult rhs = greaterThan.getRightSide().accept(this);
        return lhs.greaterThan(rhs);
    }

    @Override
    public ExpressionResult visit(LessEqual lessEqual) {
        ExpressionResult lhs = lessEqual.getLeftSide().accept(this);
        ExpressionResult rhs = lessEqual.getRightSide().accept(this);
        return lhs.lessEqual(rhs);
    }

    @Override
    public ExpressionResult visit(LessThan lessThan) {
        ExpressionResult lhs = lessThan.getLeftSide().accept(this);
        ExpressionResult rhs = lessThan.getRightSide().accept(this);
        return lhs.lessThan(rhs);
    }

    @Override
    public ExpressionResult visit(NotEqual notEqual) {
        ExpressionResult lhs = notEqual.getLeftSide().accept(this);
        ExpressionResult rhs = notEqual.getRightSide().accept(this);
        return lhs.notEquals(rhs);
    }

    @Override
    public ExpressionResult visit(LogicalAnd logicalAnd) {
        ExpressionResult lhs = logicalAnd.getLeftSide().accept(this);
        ExpressionResult rhs = logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public ExpressionResult visit(LogicalOr logicalOr) {
        ExpressionResult lhs = logicalOr.getLeftSide().accept(this);
        ExpressionResult rhs = logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public ExpressionResult visit(VariableReference variableReference) {
        return variablesValues.get(variableReference.getName());
    }

    @Override
    public ExpressionResult visit(Literal literal) {
        return ExpressionResult.createExpressionResult(literal.getType(), literal.getValue());
    }

}
