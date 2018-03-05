package Nodes;

import Nodes.Term.Term;

import java.util.Date;

/**
 * Contains a parsed question with name, label, type, and an expression if applicable
 */
public class Question {
    private String name;
    private String label;
    private Type type;
    private Expression expression;
    //TODO: private boolean condition;

    //Possible values, only one is used in each expression.
    private float value_float;
    private boolean value_boolean;
    private String value_string;
    private Date value_date;

    /**
     * Creates a question with name, label, and type
     * @param name
     * @param label
     * @param type
     */
    public Question(String name, String label, String type) {
        this.name = name;
        this.label = label;
        this.type = Type.getByCode(type);
    }

    /**
     * Creates a question with name, label, type, and expression
     * @param name
     * @param label
     * @param type
     * @param expression
     */
    public Question(String name, String label, String type, Expression expression) {
        this.name = name;
        this.label = label;
        this.type = Type.getByCode(type);
        this.expression = expression;
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
     * Evaluates the expression of the question
     * @throws UnsupportedOperationException
     */
    // This function evaluates the expression (which also does typechecking) and stores the resulting value
    public void getExpressionValue() throws UnsupportedOperationException {
        Term result = expression.getValue();
        if(type.toString() == result.toString() || ((type.toString() == "money" || type.toString() == "integer") && result.toString() == "float")) {
            switch (result.toString()) {
                case "float":
                    this.value_float = result.getFloat();
                    break;
                case "boolean":
                    this.value_boolean = result.getBoolean();
                    break;
                case "string":
                    this.value_string = result.getString();
                    break;
            }
        } else {
            throw new UnsupportedOperationException(); // TODO: Change to some type error
        }
    }
}
