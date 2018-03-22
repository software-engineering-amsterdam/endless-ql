package ql.gui.model;

import ql.ast.model.declarations.TypeDeclaration;
import ql.ast.model.expressions.Expression;
import ql.gui.controller.FormController;
import ql.gui.view.QuestionPanel;
import ql.logic.type.MixedValue;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QuestionModel {

    private final String label;
    private final String variableName;
    private final TypeDeclaration originalDataTypeDeclaration;

    private final Expression visibilityCondition;
    private final Expression assignedExpression;

    private Boolean visibility;
    private MixedValue value;

    private FormController formController;
    private QuestionPanel panel;

    public QuestionModel(String label, String variableName, TypeDeclaration originalDataTypeDeclaration, Expression visibilityCondition, Expression assignedExpression) {

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

    public Object getJavaTypedValue() {
        switch (this.value.getType()) {
            case INTEGER:
                return this.value.getIntegerValue();
            case DECIMAL:
                return this.value.getDecimalValue();
            case BOOLEAN:
                return this.value.getBooleanValue();
            case STRING:
                return this.value.getStringValue();
            default:
                return null;
        }
    }

    public void setValue(MixedValue value) {
        this.value = value;
    }

    public void changeValue(Boolean value) {
        this.value.setBooleanValue(value);
        this.formController.processQuestionModelChange(this);
    }

    public void changeValue(BigDecimal value) {
        this.value.setDecimalValue(value);
        this.formController.processQuestionModelChange(this);
    }

    public void changeValue(BigInteger value) {
        this.value.setIntegerValue(value);
        this.formController.processQuestionModelChange(this);
    }

    public void changeValue(String value) {
        this.value.setStringValue(value);
        this.formController.processQuestionModelChange(this);
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "label='" + label + '\'' +
                ", variableName='" + variableName + '\'' +
                ", originalDataTypeDeclaration=" + originalDataTypeDeclaration +
                ", visibilityCondition=" + visibilityCondition +
                ", assignedExpression=" + assignedExpression +
                ", visibility=" + visibility +
                ", value=" + value +
                '}';
    }

    public void registerController(FormController formController) {
        this.formController = formController;
    }

    public void setPanel(QuestionPanel panel) {
        this.panel = panel;
    }

    public QuestionPanel getPanel() {
        return panel;
    }
}
