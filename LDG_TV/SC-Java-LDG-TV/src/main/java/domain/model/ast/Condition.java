package domain.model.ast;

import domain.model.variable.BooleanVariable;
import domain.model.variable.Variable;

public class Condition {

    private Variable variable;
    private String operator;

    public Condition(Variable variable, String operator) {
        this.variable = variable;
        this.operator = operator;
    }

    public Variable getVariable() {
        return variable;
    }

    public String getOperator() {
        return operator;
    }
}
