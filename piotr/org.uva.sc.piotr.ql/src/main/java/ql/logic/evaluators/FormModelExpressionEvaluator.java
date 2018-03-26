package ql.logic.evaluators;

import ql.ast.model.expressions.values.VariableReference;
import ql.gui.model.QuestionModel;
import ql.logic.type.QLDataTypeWrapper;

import java.util.List;

public class FormModelExpressionEvaluator extends AbstractExpressionEvaluator {

    private final List<QuestionModel> questionModels;

    public FormModelExpressionEvaluator(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }

    @Override
    public QLDataTypeWrapper visit(VariableReference variableReference) {
        for (QuestionModel questionModel : this.questionModels) {
            if (questionModel.getVariableName().equals(variableReference.getName())) {
                return questionModel.getQLDataTypeValue();
            }
        }
        return null;
    }
}
