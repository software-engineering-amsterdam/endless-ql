package gui.model;

import ast.model.expressions.Expression;
import types.DataType;

public class FormBlock {

    private String label;
    private String variableName;
    private DataType.Type variableType;
    private Expression visibilityCondition;
    private Expression valueExpression;
    private String value;

    public FormBlock(String label, String variableName, DataType.Type variableType, Expression visibilityCondition, Expression valueExpression) {
        this.label = label;
        this.variableName = variableName;
        this.variableType = variableType;
        this.visibilityCondition = visibilityCondition;
        this.valueExpression = valueExpression;
    }

    public String getLabel() {
        return label;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public DataType.Type getVariableType() {
        return variableType;
    }

    public void setVariableType(DataType.Type variableType) {
        this.variableType = variableType;
    }

    public Expression getVisibilityCondition() {
        return visibilityCondition;
    }

    public void setVisibilityCondition(Expression visibilityCondition) {
        this.visibilityCondition = visibilityCondition;
    }

    public Expression getValueExpression() {
        return valueExpression;
    }

    public void setValueExpression(Expression valueExpression) {
        this.valueExpression = valueExpression;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
