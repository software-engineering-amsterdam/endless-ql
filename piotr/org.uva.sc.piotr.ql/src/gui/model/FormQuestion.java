package gui.model;

import ast.model.declarations.TypeDeclaration;
import ast.model.expressions.Expression;
import gui.controller.FormController;
import gui.view.FormQuestionPanel;
import logic.type.MixedValue;

import java.math.BigDecimal;

public class FormQuestion {

    private final String label;
    private final String variableName;
    private final TypeDeclaration originalDataTypeDeclaration;

    private final Expression visibilityCondition;
    private final Expression assignedExpression;

    private Boolean visibility;
    private MixedValue value;

    private FormController formController;
    private FormQuestionPanel panel;

    public FormQuestion(String label, String variableName, TypeDeclaration originalDataTypeDeclaration, Expression visibilityCondition, Expression assignedExpression) {

        this.label = label;
        this.variableName = variableName;
        this.originalDataTypeDeclaration = originalDataTypeDeclaration;

        this.visibilityCondition = visibilityCondition;
        this.visibility = true;

        this.assignedExpression = assignedExpression;
        this.value = MixedValue.createValue(this.originalDataTypeDeclaration.toDataType(), "");
    }

    public String getLabel() {
        return label;
    }

    public String getVariableName() {
        return variableName;
    }

    public TypeDeclaration getOriginalDataTypeDeclaration() {
        return originalDataTypeDeclaration;
    }

    public Expression getVisibilityCondition() {
        return visibilityCondition;
    }

    public Expression getAssignedExpression() {
        return assignedExpression;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public MixedValue getValue() {
        return value;
    }

    public void setValue(MixedValue value) {
        this.value = value;
    }

    public void changeValue(Boolean value) {
        this.value.setBooleanValue(value);
        this.formController.processFormQuestionChange(this);
    }

    public void changeValue(BigDecimal value) {
        this.value.setDecimalValue(value);
        this.formController.processFormQuestionChange(this);
    }

    public void changeValue(Integer value) {
        this.value.setIntegerValue(value);
        this.formController.processFormQuestionChange(this);
    }

    public void changeValue(String value) {
        this.value.setStringValue(value);
        this.formController.processFormQuestionChange(this);
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

    public void registerController(FormController formController) throws Exception {
        if (this.formController != null) {
            throw new Exception("Form controller can be registered only once");
        } else {
            this.formController = formController;
        }
    }

    public void setPanel(FormQuestionPanel panel) {
        this.panel = panel;
    }

    public FormQuestionPanel getPanel() {
        return panel;
    }
}
