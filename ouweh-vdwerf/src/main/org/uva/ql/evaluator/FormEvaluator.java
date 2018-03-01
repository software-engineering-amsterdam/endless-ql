package org.uva.ql.evaluator;

import org.uva.ql.ast.*;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.visitor.StatementVisitor;

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

    @Override
    public Void visit(Question question, String context) {
        this.statementTable.addQuestion(question.getName(), question);
        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion question, String context) {
        return null;
    }
}
