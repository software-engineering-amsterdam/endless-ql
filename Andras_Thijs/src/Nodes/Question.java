package Nodes;

import Nodes.Term.QLBoolean;
import Nodes.Term.Term;
import QLExceptions.*;

/**
 * Contains a parsed question with name, label, type, and an expression if applicable
 */
public class Question extends ASTNode {
    private String name;
    private String label;
    private Type type;
    public Expression expression; //TODO: TESTING PURPOSES, THIS SHOULD BE PRIVATE!!!

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
            // TODO code smell! remove if possible
            switch(result.getType()) {
                case BOOL: if(type == Type.BOOL) return;
                case DECIMAL: if(type == Type.DECIMAL || type == Type.INT || type == Type.MONEY) break;
                case STRING: if(type == Type.STRING || type == Type.DATE) break;
                default: throw new TypeException(this, type, Type.getByCode(result.toString()));
            }
            this.result = result;
        } catch(OtherException e) {
            // This Exception is thrown when a Variable isn't set yet.
            result = null;
        }
    }

    public boolean isAvailable() throws TypeException, SyntaxException, OtherException {
        return this.getParent().isAvailable();
    }
}
