package gui.model;

import ast.model.expressions.Expression;
import ast.visitors.evaluators.ExpressionResult;

public class FormQuestion {

    private String label;
    private String variableName;
    private Expression.DataType variableDataType;
    private Expression visibilityCondition;
    private Expression assignedExpression;
    private ExpressionResult visibility;
    private ExpressionResult value;

    public FormQuestion(String label, String variableName, Expression.DataType variableDataType, Expression visibilityCondition, Expression assignedExpression) {
        this.label = label;
        this.variableName = variableName;
        this.variableDataType = variableDataType;
        this.visibilityCondition = visibilityCondition;
        this.assignedExpression = assignedExpression;
        // default value
        this.value = ExpressionResult.createExpressionResult(this.variableDataType, "");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression.DataType getVariableDataType() {
        return variableDataType;
    }

    public void setVariableDataType(Expression.DataType variableDataType) {
        this.variableDataType = variableDataType;
    }

    public Expression getVisibilityCondition() {
        return visibilityCondition;
    }

    public void setVisibilityCondition(Expression visibilityCondition) {
        this.visibilityCondition = visibilityCondition;
    }

    public Expression getAssignedExpression() {
        return assignedExpression;
    }

    public void setAssignedExpression(Expression assignedExpression) {
        this.assignedExpression = assignedExpression;
    }

    public ExpressionResult getVisibility() {
        return visibility;
    }

    public void setVisibility(ExpressionResult visibility) {
        this.visibility = visibility;
    }

    public ExpressionResult getValue() {
        return value;
    }

    public void setValue(ExpressionResult value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FormQuestion{" +
                "label='" + label + '\'' +
                ", variableName='" + variableName + '\'' +
                ", variableDataType=" + variableDataType.name() +
                ", visibilityCondition=" + visibilityCondition +
                ", assignedExpression=" + assignedExpression +
                ", visibility=" + visibility +
                ", value=" + value +
                '}';
    }
}
