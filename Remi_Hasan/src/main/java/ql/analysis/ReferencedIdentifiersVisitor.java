package ql.analysis;

import ql.evaluation.Binding;
import ql.evaluation.IExpressionVisitor;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.util.ArrayList;
import java.util.List;

public class ReferencedIdentifiersVisitor implements IExpressionVisitor<List<String>> {

    public List<Binding> getBindings(Form form){
        List<Binding> bindings = new ArrayList<>();
        for(Question question : form.questions){
            Binding binding;
            if(question.isComputed()){
                binding = new Binding(question.name, question.defaultAnswer);
            } else {
                binding = new Binding(question.name, new ExpressionVariableUndefined(null, question.type));
            }
            bindings.add(binding);
        }
        return bindings;
    }

    private List<String> visitLeftRight(Expression left, Expression right, List<Binding> bindings) {
        List<String> identifiers = left.accept(this, bindings);
        identifiers.addAll(right.accept(this, bindings));
        return identifiers;
    }

    @Override
    public List<String> visit(Expression expression, List<Binding> bindings) {
        return expression.accept(this, bindings);
    }

    @Override
    public List<String> visit(ExpressionArithmeticDivide expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionArithmeticMultiply expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSubtract expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSum expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionComparisonEq expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionComparisonGE expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionComparisonGT expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionComparisonLE expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionComparisonLT expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionLogicalAnd expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionLogicalOr expression, List<Binding> bindings) {
        return visitLeftRight(expression.left, expression.right, bindings);
    }

    @Override
    public List<String> visit(ExpressionUnaryNot expression, List<Binding> bindings) {
        return expression.value.accept(this, bindings);
    }

    @Override
    public List<String> visit(ExpressionUnaryNeg expression, List<Binding> bindings) {
        return expression.value.accept(this, bindings);
    }

    @Override
    public List<String> visit(ExpressionVariableBoolean expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDate expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableInteger expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDecimal expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableMoney expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableString expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableUndefined expression, List<Binding> bindings) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionIdentifier expression, List<Binding> bindings) {
        List<String> listWithIdentifier = new ArrayList<>();
        listWithIdentifier.add(expression.identifier);
        return listWithIdentifier;
    }
}
