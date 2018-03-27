package ql.logic.collectors;

import ql.ast.model.Form;
import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.binary.logical.LogicalAnd;
import ql.ast.model.expressions.unary.logical.Negation;
import ql.ast.model.statements.IfStatement;
import ql.ast.model.statements.Question;
import ql.ast.model.statements.Statement;
import ql.ast.visitors.AbstractASTTraverse;
import ql.gui.model.QuestionModel;
import ql.logic.type.QLDataTypeWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CollectQuestionModelsVisitor extends AbstractASTTraverse {

    private List<QuestionModel> questionModels = new ArrayList<>();
    private Stack<Expression> conditionsStack = new Stack<>();

    public List<QuestionModel> getQuestionModels(Form form) {
        this.questionModels = new ArrayList<>();
        this.conditionsStack = new Stack<>();
        form.accept(this);
        return questionModels;
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question question) {

        Expression aggregatedVisibilityCondition = this.aggregateConditionsStack();

        // strips question and flattens visibility condition (for ql.gui rendering ease)
        QuestionModel questionModel = new QuestionModel(
                question.getLabel(),
                question.getVariableName(),
                question.getVariableType(),
                aggregatedVisibilityCondition,
                question.getAssignedExpression()
        );

        this.questionModels.add(questionModel);

        question.getVariableType().accept(this);
        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        ifStatement.getCondition().accept(this);

        this.conditionsStack.push(ifStatement.getCondition());

        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }

        // flip the condition on the stack to negation
        this.conditionsStack.push(new Negation(this.conditionsStack.pop()));

        for (Statement statement : ifStatement.getElseStatementList()) {
            statement.accept(this);
        }

        this.conditionsStack.pop();

        return null;
    }

    private Expression aggregateConditionsStack() {

        Expression finalExpression = null;

        for (Expression expression : this.conditionsStack) {
            if (finalExpression == null) {
                finalExpression = expression;
            } else {
                finalExpression = new LogicalAnd(finalExpression, expression);
            }
        }

        return finalExpression;
    }

    public HashMap<String, QLDataTypeWrapper> getVariablesValues() {

        HashMap<String, QLDataTypeWrapper> variablesValues = new HashMap<>();
        for (QuestionModel questionModel : this.questionModels) {
            variablesValues.put(questionModel.getVariableName(), questionModel.getQLDataTypeValue());
        }
        return variablesValues;
    }
}
