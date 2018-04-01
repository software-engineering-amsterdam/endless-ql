package ql.logic.evaluators;

import ql.ast.model.expressions.values.VariableReference;
import ql.gui.model.FormModel;
import ql.gui.model.QuestionModel;
import ql.logic.type.QLDataTypeWrapper;

public class FormModelExpressionEvaluator extends AbstractExpressionEvaluator {

    private final FormModel formModel;

    public FormModelExpressionEvaluator(FormModel formModel) {
        this.formModel = formModel;
    }

    @Override
    public QLDataTypeWrapper visit(VariableReference variableReference) {
        for (QuestionModel questionModel : this.formModel.getQuestionModels()) {
            if (questionModel.getVariableName().equals(variableReference.getName())) {
                return questionModel.getQLDataTypeValue();
            }
        }
        return null;
    }
}
