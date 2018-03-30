package ql.gui.model;

import ql.ast.model.declarations.TypeDeclaration;
import ql.ast.model.expressions.Expression;
import ql.ast.model.statements.Question;
import ql.gui.controller.FormController;
import ql.gui.view.QuestionView;
import ql.logic.type.QLDataTypeWrapper;

public class QuestionModel {

    private final Question question;

    private final Expression visibilityCondition;

    private Boolean visibility;
    private QLDataTypeWrapper qlDataTypeWrapperValue;

    private QuestionView questionView;
    private FormController formController;

    public QuestionModel(Question question, Expression visibilityCondition) {

        this.question = question;
        this.visibilityCondition = visibilityCondition;
        this.visibility = true;

        this.qlDataTypeWrapperValue = QLDataTypeWrapper.createValue(this.question.getVariableType().toDataType(), "");
    }

    public String getQuestionLabel() {
        return this.question.getLabel();
    }

    public String getVariableName() {
        return this.question.getVariableName();
    }

    public TypeDeclaration getOriginalDataTypeDeclaration() {
        return this.question.getVariableType();
    }

    public Expression getVisibilityCondition() {
        return visibilityCondition;
    }

    public Expression getAssignedExpression() {
        return question.getAssignedExpression();
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
        this.qlDataTypeWrapperValue.setValue(value); // TODO: hmm?
        // formController has to be informed
        this.formController.processQuestionModelChange(this);
    }

    public void setQuestionView(QuestionView questionView) {
        this.questionView = questionView;
    }

    public QuestionView getQuestionView() {
        return questionView;
    }

    public void registerController(FormController formController) {
        this.formController = formController;
    }
}
