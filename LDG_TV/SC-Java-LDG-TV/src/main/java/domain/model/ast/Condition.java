package domain.model.ast;

import domain.model.variable.Variable;

public class Condition {

    private Variable variable;
    private String operator;

    public Condition(Variable variable) {
        this.variable = variable;
        this.operator = null;
    }

    public Variable getVariable() {
        return variable;
    }

    public Object getComputedValue() {
        return variable.getComputedValue();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
