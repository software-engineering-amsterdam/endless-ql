package ql.gui.model;

import ql.ast.model.declarations.TypeDeclaration;
import ql.ast.model.expressions.Expression;
import ql.gui.controller.FormController;
import ql.gui.view.QuestionPanel;
import ql.logic.type.QLDataTypeWrapper;

public class QuestionModel {

    private final String label;
    private final String variableName;
    private final TypeDeclaration originalDataTypeDeclaration;

    private final Expression visibilityCondition;
    private final Expression assignedExpression;

    private Boolean visibility;
    private QLDataTypeWrapper qlDataTypeWrapperValue;

    private FormController formController;
    private QuestionPanel panel;

    public QuestionModel(String label, String variableName, TypeDeclaration originalDataTypeDeclaration, Expression visibilityCondition, Expression assignedExpression) {

        this.label = label;
        this.variableName = variableName;
        this.originalDataTypeDeclaration = originalDataTypeDeclaration;

        this.visibilityCondition = visibilityCondition;
        this.visibility = true;

        this.assignedExpression = assignedExpression;
        this.qlDataTypeWrapperValue = QLDataTypeWrapper.createValue(this.originalDataTypeDeclaration.toDataType(), "");
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

    public QLDataTypeWrapper getQLDataTypeValue() {
        return qlDataTypeWrapperValue;
    }

    public Object getJavaTypedValue() {
        return this.qlDataTypeWrapperValue.getValue();
    }

    public void setQlTypedValue(QLDataTypeWrapper value) {
        this.qlDataTypeWrapperValue = value;
    }

    public void changeValue(Object value) {
        this.qlDataTypeWrapperValue.setValue(value);
        this.formController.processQuestionModelChange(this);
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
