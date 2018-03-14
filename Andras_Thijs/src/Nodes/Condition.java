package Nodes;

import java.util.List;

/**
 * A parsed condition that can contain questions and other conditions
 */
public class Condition extends ASTNode {
    private Expression expression;
    private List<Question> questions;
    private List<Condition> conditions;

    /**
     * Creates an empty condition with just an expression
     * @param expression contains an Expression
     */
    public Condition(Expression expression){
        this.expression = expression;
    }

    /**
     * Creates a condition with an expression and a list of questions or conditions
     * @param expression contains an Expression
     * @param nodes contains either a list of Questions, or a list of Conditions
     * @throws UnsupportedOperationException when the type is not Question or Condition
     */
    public Condition(Expression expression, List<? extends ASTNode> nodes){
        this.expression = expression;
        ASTNode first = nodes.get(0);
        if(first instanceof Question) {
            this.questions = (List<Question>) nodes;
        } else if(first instanceof Condition) {
            this.conditions = (List<Condition>) nodes;
        } else {
            throw new UnsupportedOperationException(); //TODO: Maybe change this?
        }
    }

    /**
     * Creates a condition with an expression, a list of questions, and a list of conditions
     * @param expression contains an Expression
     * @param questions contains a list of Questions
     * @param conditions contains a list of Conditions
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
