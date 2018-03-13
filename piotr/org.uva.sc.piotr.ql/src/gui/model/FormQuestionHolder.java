package gui.model;

import ast.model.declarations.TypeDeclaration;
import ast.model.expressions.Expression;
import gui.controller.FormController;

import java.math.BigDecimal;
import java.util.List;

public class FormQuestionHolder {

    private String label;
    private String variableName;
    private TypeDeclaration originalDataTypeDeclaration;

    private Expression visibilityCondition;
    private Expression assignedExpression;

    private MixedValueHolder visibilityHolder;
    private MixedValueHolder valueHolder;

    private FormController formController;

    public FormQuestionHolder(String label, String variableName, TypeDeclaration originalDataTypeDeclaration, Expression visibilityCondition, Expression assignedExpression) {
        this.label = label;
        this.variableName = variableName;
        this.originalDataTypeDeclaration = originalDataTypeDeclaration;
        this.visibilityCondition = visibilityCondition;
        this.assignedExpression = assignedExpression;
        // default value
        this.valueHolder = MixedValueHolder.createValueHolder(this.originalDataTypeDeclaration.toDataType(), "");
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

    public MixedValueHolder getVisibilityHolder() {
        return visibilityHolder;
    }

    public void setVisibilityHolder(MixedValueHolder visibilityHolder) {
        this.visibilityHolder = visibilityHolder;
    }

    public MixedValueHolder getValueHolder() {
        return valueHolder;
    }

    public void setValueHolder(MixedValueHolder valueHolder) {
        this.valueHolder = valueHolder;
    }

    public void changeValue(Boolean value) {
        System.out.println("Change value " + value.getClass().getSimpleName());
        this.valueHolder.setBooleanValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    public void changeValue(BigDecimal value) {
        System.out.println("Change value " + value.getClass().getSimpleName());
        this.valueHolder.setDecimalValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    public void changeValue(Integer value) {
        System.out.println("Change value " + value.getClass().getSimpleName());
        this.valueHolder.setIntegerValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    public void changeValue(String value) {
        System.out.println("Change value " + value.getClass().getSimpleName());
        this.valueHolder.setStringValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    @Override
    public String toString() {
        return "FormQuestionHolder{" +
                "label='" + label + '\'' +
                ", variableName='" + variableName + '\'' +
                ", originalDataTypeDeclaration=" + originalDataTypeDeclaration +
                ", visibilityCondition=" + visibilityCondition +
                ", assignedExpression=" + assignedExpression +
                ", visibility=" + visibilityHolder +
                ", value=" + valueHolder +
                '}';
    }

    public void registerController(FormController formController) throws Exception {
        if (this.formController != null) {
            throw new Exception("Form controller can be registered only once");
        } else {
            this.formController = formController;
        }
    }
}
