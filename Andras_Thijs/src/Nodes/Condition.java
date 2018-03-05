package Nodes;

import java.util.List;

/**
 * A parsed condition that can contain questions and other conditions
 */
public class Condition {
    private Expression expression;
    private List<Question> questions;
    private List<Condition> conditions;

    /**
     * Creates an empty condition with just an expression
     * @param expression
     */
    public Condition(Expression expression){
        this.expression = expression;
    }

    /**
     * Creates a condition with an expression and a list of questions
     * @param expression
     * @param questions
     */
    public Condition(Expression expression, List<Question> questions){
        this.expression = expression;
        this.questions = questions;
    }

    /**
     * Creates a condition with an expression, a list of questions, and a list of conditions
     * @param expression
     * @param questions
     * @param conditions
     */
    public Condition(Expression expression, List<Question> questions, List<Condition> conditions){
        this.expression = expression;
        this.questions = questions;
        this.conditions = conditions;
    }

    /**
     * Returns the condition's evaluable expression
     * @return The expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Returns the condition's list of condition
     * @return The list of conditions
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * Returns the condition's list of questions
     * @return The list of questions
     */
    public List<Question> getQuestions() {
        return questions;
    }
}
