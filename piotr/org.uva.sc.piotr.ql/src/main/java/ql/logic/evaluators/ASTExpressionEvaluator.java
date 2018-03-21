package ql.logic.evaluators;

import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.logic.type.MixedValue;

import java.util.HashMap;
import java.util.List;

public class ASTExpressionEvaluator extends AbstractExpressionEvaluator {

    private HashMap<String, MixedValue> variablesRegister = new HashMap<>();

    public ASTExpressionEvaluator(List<Question> questions) {
        // default empty values for variables register
        for (Question question : questions) {
            this.variablesRegister.put(question.getVariableName(), MixedValue.createValue(question.getVariableType().toDataType(), ""));
        }
    }

    @Override
    public MixedValue visit(VariableReference variableReference) {
        return this.variablesRegister.get(variableReference.getName());
    }

}
