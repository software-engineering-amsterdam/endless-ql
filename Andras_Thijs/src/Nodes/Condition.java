package Nodes;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;
import QLExceptions.*;

import java.util.List;

/**
 * A parsed condition that can contain questions and other conditions
 */
public class Condition extends ASTNode {
    private final Expression expression;
    private final List<Question> questions;
    private final List<Condition> conditions;

    private QLBoolean result;

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
     * This sets the parent of this Condition and it's children's parents
     * @param parent this ASTNode's parent
     */
    public void setParents(ASTNode parent) {
        setParent(parent);
        expression.setParents(this);

        for(Question q : questions)
            q.setParents(this);

        for(Condition c : conditions)
            c.setParents(this);
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

    /**
     * Evaluates the expression of the question
     * @throws TypeException when the resulting Term is not QLBoolean.
     */
    // This function evaluates the expression (which also does type checking) and stores the resulting value
    private void getExpressionValue() throws TypeException, SyntaxException {
        try {
            Term result = expression.getTerm();
            if(result.getType() == Type.BOOL) {
                this.result = (QLBoolean) result;
            } else {
                throw new TypeException(this, Type.BOOL, Type.getByCode(result.toString()));
            }
        } catch(OtherException e) {
            // This is thrown when a Variable isn't set yet.
            this.result = null;
        }
    }

    @Override
    public boolean isAvailable() throws SyntaxException, TypeException {
        getExpressionValue();
        return this.result != null && this.getParent().isAvailable() && this.result.getBoolean();
    }
}
