package gui.model;

import ast.model.declarations.TypeDeclaration;
import ast.model.expressions.Expression;
import gui.controller.FormController;
import gui.view.FormQuestionPanel;

import java.math.BigDecimal;

public class FormQuestionHolder {

    private final String label;
    private final String variableName;
    private final TypeDeclaration originalDataTypeDeclaration;

    private final Expression visibilityCondition;
    private final Expression assignedExpression;

    private MixedValueHolder visibilityHolder;
    private MixedValueHolder valueHolder;

    private FormController formController;
    private FormQuestionPanel panel;

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
        this.valueHolder.setBooleanValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    public void changeValue(BigDecimal value) {
        this.valueHolder.setDecimalValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    public void changeValue(Integer value) {
        this.valueHolder.setIntegerValue(value);
        this.formController.processFormQuestionHolderChange(this);
    }

    public void changeValue(String value) {
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

    public void setPanel(FormQuestionPanel panel) {
        this.panel = panel;
    }

    public FormQuestionPanel getPanel() {
        return panel;
    }
}
