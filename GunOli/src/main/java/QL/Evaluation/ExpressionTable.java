package QL.Evaluation;

import QL.AST.Expressions.Expression;
import QL.AST.Form;
import QL.AST.Question;
import QL.Visitors.QuestionVisitorInterface;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTable implements QuestionVisitorInterface {
    private Map<String, Expression> expressionTable;
    private final Form form;

    public ExpressionTable(Form form){
        this.form = form;
        this.expressionTable = new HashMap<>();
        populateExpressionTable();
    }

    public Expression getExpression(String id){
        return expressionTable.get(id);
    }

    public void updateExpression(String id, Expression expr){
        this.expressionTable.replace(id, expr);
    }

    public Boolean containsExpression(String id){
        return this.expressionTable.containsKey(id);
    }

    public void populateExpressionTable(){
        for(Question question : this.form.getQuestions()){
            question.accept(this);
        }
    }

    @Override
    public Object visit(Question question) {
        expressionTable.put(question.getIdentifier(), question.getAnswer());
        return null;
    }
}
