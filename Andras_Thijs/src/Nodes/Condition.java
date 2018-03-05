package Nodes;

import java.util.List;

public class Condition {
    private Expression expression;
    private List<Question> questions;
    private List<Condition> conditions;

    public Condition(Expression expression){
        this.expression = expression;
    }

    public Condition(Expression expression, List<Question> questions){
        this.expression = expression;
        this.questions = questions;
    }

    public Condition(Expression expression, List<Question> questions, List<Condition> conditions){
        this.expression = expression;
        this.questions = questions;
        this.conditions = conditions;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
