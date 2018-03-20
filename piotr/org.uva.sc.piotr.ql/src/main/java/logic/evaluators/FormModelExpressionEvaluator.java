package logic.evaluators;

import ast.model.expressions.values.VariableReference;
import gui.model.QuestionModel;
import logic.type.MixedValue;

import java.util.List;

public class FormModelExpressionEvaluator extends AbstractExpressionEvaluator {

    private final List<QuestionModel> questionModels;

    public FormModelExpressionEvaluator(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }

    @Override
    public MixedValue visit(VariableReference variableReference) {
        for (QuestionModel questionModel : this.questionModels) {
            if (questionModel.getVariableName().equals(variableReference.getName())) {
                return questionModel.getValue();
            }
        }
        return null;
    }
}
