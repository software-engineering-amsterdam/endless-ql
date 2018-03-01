package org.uva.ql.evaluator;

import org.uva.ql.ast.*;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.visitor.StatementVisitor;
import java.util.List;

public class FormEvaluator implements StatementVisitor<Void, String>{

    private final ExpressionTable expressionTable;
    private final StatementTable statementTable;
    private final Form form;

    public FormEvaluator(ExpressionTable expressionTable, StatementTable statementTable, Form form){
        this.expressionTable = expressionTable;
        this.statementTable = statementTable;
        this.form = form;

        for (Statement statement : form.getStatements()){
            statement.accept(this, null);
        }
    }

    public List<Question> getQuestionsAsList() {
        return this.statementTable.getQuestionsAsList();
    }

    @Override
    public Void visit(Question question, String context) {
        this.statementTable.addQuestion(question.getName(), question);
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
        return null;
    }
}
