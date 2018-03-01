package org.uva.ql.evaluator;

import org.uva.ql.ast.*;
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

    public Value getValueById(String id) {
        return this.valueTable.getValueByID(id);
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
            this.statementTable.addConditional(conditional.toString(), conditional);
            statement.accept(this, context);
        }
        for (Statement statement : conditional.getElseSide()) {
            this.statementTable.addConditional(conditional.toString(), conditional);
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
