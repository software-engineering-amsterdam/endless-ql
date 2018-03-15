package Nodes;

import Nodes.Term.Term;
import QLExceptions.*;

/**
 * Contains a parsed question with name, label, type, and an expression if applicable
 */
public class Question extends ASTNode {
    private String name;
    private String label;
    private Type type;
    private Expression expression;

    private boolean isDisplayed = false;
    private Term result;

    /**
     * Creates a question with name, label, and type
     * @param name contains the name of a Question (which will act as a variable)
     * @param label contains the label of a Question
     * @param type contains the Type of a Question
     */
    public Question(String name, String label, String type) {
        this.name = name;
        this.label = label;
        this.type = Type.getByCode(type);
    }

    /**
     * Creates a question with name, label, type, and expression
     * @param name contains the name of a Question (which will act as a variable)
     * @param label contains the label of a Question
     * @param type contains the Type of a Question
     * @param expression contains an Expression of a Question
     */
    public Question(String name, String label, String type, Expression expression) {
        this.name = name;
        this.label = label;
        this.type = Type.getByCode(type);
        this.expression = expression;
    }

    /**
     * This sets the parent of this Condition and it's children's parents
     * @param parent this ASTNode's parent
     */
    public void setParents(ASTNode parent) {
        setParent(parent);
        if(expression != null)
            expression.setParents(this);
    }

    /**
     * Returns the name of the question
     * @return The name of the question
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the label of the question
     * @return The label of the question
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the type of the question
     * @return The type of the question
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the result of a question
     * @return the term of a question, used for example as variable
     */
    public Term getResult() { return result; }

    /**
     * Evaluates the expression of the question
     * @throws TypeException when the Types don't match
     */
    // This function evaluates the expression (which also does typechecking) and stores the resulting value
    public void getExpressionValue() throws TypeException, SyntaxException {
        try {
            Term result = expression.getTerm();
            if (type.toString().equals(result.toString()) || ((type.toString().equals("money") || type.toString().equals("integer")) && result.toString().equals("float"))) {
                this.result = result;
            } else {
                throw new TypeException(this, type, Type.getByCode(result.toString()));
            }
        } catch(OtherException e) {
            // This Exception is thrown when a Variable isn't set yet.
            result = null;
        } catch (SyntaxException e) {
            //TODO: Remove this catch once the method is called from Main
            throw e;
        }
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }

    // TODO implement recursive function to check expressions up the hiearchy
    public boolean isAvailable() {
        return true;
    }
}
