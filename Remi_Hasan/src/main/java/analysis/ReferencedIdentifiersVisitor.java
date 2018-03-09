package analysis;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;

import java.util.ArrayList;
import java.util.List;

public class ReferencedIdentifiersVisitor implements IASTVisitor<List<String>> {

    private List<String> visitLeftRight(Expression left, Expression right) {
        List<String> identifiers = left.accept(this);
        identifiers.addAll(right.accept(this));
        return identifiers;
    }

    @Override
    public List<String> visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public List<String> visit(ExpressionArithmeticDivide expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticMultiply expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSubtract expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSum expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonEq expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGE expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGT expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLE expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLT expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalAnd expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalOr expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionUnaryNot expression) {
        return expression.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionUnaryNeg expression) {
        return expression.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionVariableBoolean expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDate expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableInteger expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDecimal expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableMoney expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableString expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableUndefined expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionIdentifier expression) {
        List<String> listWithIdentifier = new ArrayList<>();
        listWithIdentifier.add(expression.identifier);
        return listWithIdentifier;
    }
}
