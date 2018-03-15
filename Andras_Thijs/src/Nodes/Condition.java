package Nodes;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;
import QLExceptions.*;

import java.util.List;

/**
 * A parsed condition that can contain questions and other conditions
 */
public class Condition extends ASTNode {
    private Expression expression;
    private List<Question> questions;
    private List<Condition> conditions;

    private QLBoolean result;

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
     * @throws SyntaxException when the type is not Question or Condition
     */
    public Condition(Expression expression, List<? extends ASTNode> nodes) throws SyntaxException {
        this.expression = expression;
        ASTNode first = nodes.get(0);
        if(first instanceof Question) {
            this.questions = (List<Question>) nodes;
        } else if(first instanceof Condition) {
            this.conditions = (List<Condition>) nodes;
        } else {
            throw new SyntaxException();
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
     * This sets the parent of this Condition and it's children's parents
     * @param parent this ASTNode's parent
     */
    public void setParents(ASTNode parent) {
        setParent(parent);
        expression.setParents(this);

        if(questions != null)
            for(Question q : questions)
                q.setParents(this);

        if(conditions != null)
            for(Condition c : conditions)
                c.setParents(this);
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

    public QLBoolean getResult() { return result; }

    /**
     * Evaluates the expression of the question
     * @throws TypeException when the resulting Term is not QLBoolean.
     */
    // This function evaluates the expression (which also does typechecking) and stores the resulting value
    public void getExpressionValue() throws TypeException, SyntaxException {
        try {
            Term result = expression.getTerm();
            if (result.toString().equals("qlboolean")) {
                this.result = (QLBoolean) result;
            } else {
                throw new TypeException(Type.BOOL, Type.getByCode(result.toString()));
            }
        } catch(OtherException e) {
            // This is thrown when a Variable isn't set yet.
            result = null;
        } catch (SyntaxException e) {
            //TODO: Remove this catch once the method is called from Main
            throw e;
        }
    }
}
