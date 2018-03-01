package Nodes;

import Nodes.Term.Term;

import java.util.Date;

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

    public Question(String name, String label, String type) {
        this.name = name;
        this.label = label;
        this.type = Type.getByCode(type);
    }

    public Question(String name, String label, String type, Expression expression) {
        this.name = name;
        this.label = label;
        this.type = Type.getByCode(type);
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Type getType() {
        return type;
    }

    // This function evaluates the expression (which also does typechecking) and stores the resulting value.
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
