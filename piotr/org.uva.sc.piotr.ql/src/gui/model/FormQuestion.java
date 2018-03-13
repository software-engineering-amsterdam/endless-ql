package gui.model;

import ast.model.declarations.TypeDeclaration;
import ast.model.expressions.Expression;
import logic.evaluators.UniversalTypeValue;

public class FormQuestion {

    private String label;
    private String variableName;
    private TypeDeclaration originalDataTypeDeclaration;
    private Expression visibilityCondition;
    private Expression assignedExpression;
    private UniversalTypeValue visibility;
    private UniversalTypeValue value;

    public FormQuestion(String label, String variableName, TypeDeclaration originalDataTypeDeclaration, Expression visibilityCondition, Expression assignedExpression) {
        this.label = label;
        this.variableName = variableName;
        this.originalDataTypeDeclaration = originalDataTypeDeclaration;
        this.visibilityCondition = visibilityCondition;
        this.assignedExpression = assignedExpression;
        // default value
        this.value = UniversalTypeValue.createValue(this.originalDataTypeDeclaration.toDataType(), "");
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

    public TypeDeclaration getOriginalDataTypeDeclaration() {
        return originalDataTypeDeclaration;
    }

    public void setOriginalDataTypeDeclaration(TypeDeclaration originalDataTypeDeclaration) {
        this.originalDataTypeDeclaration = originalDataTypeDeclaration;
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

    public UniversalTypeValue getVisibility() {
        return visibility;
    }

    public void setVisibility(UniversalTypeValue visibility) {
        this.visibility = visibility;
    }

    public UniversalTypeValue getValue() {
        return value;
    }

    public void setValue(UniversalTypeValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FormQuestion{" +
                "label='" + label + '\'' +
                ", variableName='" + variableName + '\'' +
                ", originalDataTypeDeclaration=" + originalDataTypeDeclaration +
                ", visibilityCondition=" + visibilityCondition +
                ", assignedExpression=" + assignedExpression +
                ", visibility=" + visibility +
                ", value=" + value +
                '}';
    }
}
