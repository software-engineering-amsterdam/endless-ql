package org.uva.ql.evaluator;

import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.ast.type.StringType;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.visitor.StatementVisitor;
import org.uva.ql.visitor.TypeVisitor;
import java.util.List;

public class FormEvaluator implements StatementVisitor<Void, String>, TypeVisitor<Void, String>{

    private final ExpressionTable expressionTable;
    private final StatementTable statementTable;
    private final ValueTable valueTable;
    private final Form form;

    public FormEvaluator(ExpressionTable expressionTable, StatementTable statementTable, ValueTable valueTable, Form form){
        this.expressionTable = expressionTable;
        this.statementTable = statementTable;
        this.valueTable = valueTable;
        this.form = form;

        for (Statement statement : form.getStatements()){
            statement.accept(this, null);
        }
    }

    public List<Question> getQuestionsAsList() {
        return this.statementTable.getQuestionsAsList();
    }

    public ValueTable getValueTable() {
        return valueTable;
    }

    public void addOrUpdateValue(String id, Value value){
        this.valueTable.addOrUpdateValue(id, value);
    }

    public Expression getConditionById(String id){
        return this.statementTable.getConditionByQuestionID(id);
    }

    public boolean questionHasCondition(Question question){
        return this.statementTable.questionIsConditional(question.toString());
    }

    public Value getValueById(String id) {
        return this.valueTable.getValueByID(id);
    }

    public void evaluateAllExpressions(ExpressionEvaluator expressionEvaluator){
        for (Question question : this.getQuestionsAsList()){
            if(this.expressionTable.questionHasExpression(question.toString())){
                Value value = expressionEvaluator.evaluateExpression(question.getName(),this.expressionTable.getExpressionByName(question.getName()),this.valueTable);
                this.valueTable.addOrUpdateValue(question.getName(), value);
            }
        }
    }

    @Override
    public Void visit(Question question, String context) {
        this.statementTable.addQuestion(question.getName(), question);
        question.getType().accept(this, question.getName());
        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {
        for (Statement statement : conditional.getIfSide()) {
            this.statementTable.addConditional(statement.toString(), conditional.getCondition());
            statement.accept(this, context);
        }
        for (Statement statement : conditional.getElseSide()) {
            this.statementTable.addConditional(statement.toString(), conditional.getCondition());
            statement.accept(this, context);
        }
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion question, String context) {
        this.statementTable.addQuestion(question.getName(), question);
        this.expressionTable.addExpression(question.getName(), question.getExpression());
        question.getType().accept(this, question.getName());
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType, String context) {
        this.valueTable.addOrUpdateValue(context, new BooleanValue(false));
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        this.valueTable.addOrUpdateValue(context, new IntegerValue(0));
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        this.valueTable.addOrUpdateValue(context, new StringValue(""));
        return null;
    }

    @Override
    public Void visit(MoneyType moneyType, String context) {
        this.valueTable.addOrUpdateValue(context, new IntegerValue(0));
        return null;
    }
}
