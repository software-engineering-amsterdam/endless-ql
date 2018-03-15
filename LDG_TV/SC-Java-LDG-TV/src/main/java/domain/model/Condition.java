package domain.model;

import domain.model.variable.BooleanVariable;
import domain.model.variable.Variable;

public class Condition {

    private BooleanVariable variable;
    private String operator;

    public Condition(BooleanVariable variable, String operator) {
        this.variable = variable;
        this.operator = operator;
    }

    public BooleanVariable getVariable() {
        return variable;
    }

    public String getOperator() {
        return operator;
    }
}
