package org.uva.ql.validation;

import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.ParameterGroup;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.visitor.ExpressionVisitor;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterChecker extends Checker implements StatementVisitor<Void, String>, ExpressionVisitor<Void, String> {

    private final String ERROR_MESSAGE = "Referenced parameter does not exist: ";
    private SymbolTable symbolTable;
    private Map<String, List<Parameter>> expressions;

    ParameterChecker(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.expressions = new HashMap<>();

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    @Override
    public void runCheck() {
        for (HashMap.Entry<String, List<Parameter>> entry : expressions.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                if (!symbolTable.contains(parameter.getID())) {
                    logger.severe(ERROR_MESSAGE + parameter);
                }
            }
        }
    }

    public Map<String, List<Parameter>> getExpressions() {
        return this.expressions;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        if (!symbolTable.contains(parameter.getID())) {
            logger.severe(ERROR_MESSAGE + parameter);
            return null;
        }

        //handle calculated questions
        if (expressions.containsKey(context)) {
            expressions.get(context).add(parameter);
        } else {
            assert context != null;
        }

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {

        if (!expressions.containsKey(calculatedQuestion.getName())) {
            expressions.put(calculatedQuestion.getName(), new ArrayList<>());
        }

        calculatedQuestion.getExpression().accept(this, calculatedQuestion.getName());
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        parameterGroup.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {
        conditional.getCondition().accept(this, "Condition");
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        addition.getLeft().accept(this, context);
        addition.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        division.getLeft().accept(this, context);
        division.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        equal.getLeft().accept(this, context);
        equal.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        greaterThan.getLeft().accept(this, context);
        greaterThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        greaterThanEqualTo.getLeft().accept(this, context);
        greaterThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        lessThan.getLeft().accept(this, context);
        lessThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        lessThanEqualTo.getLeft().accept(this, context);
        lessThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        multiplication.getLeft().accept(this, context);
        multiplication.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        notEqual.getLeft().accept(this, context);
        notEqual.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        subtraction.getLeft().accept(this, context);
        subtraction.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Or or, String context) {
        or.getLeft().accept(this, context);
        or.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(And and, String context) {
        and.getLeft().accept(this, context);
        and.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral, String Context) {
        return null;
    }

    @Override
    public Void visit(Negation negation, String Context) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(Question question, String context) {
        return null;
    }
}
