package ql.logic.evaluators;

import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.logic.type.QLDataTypeWrapper;

import java.util.HashMap;
import java.util.List;

public class ASTExpressionEvaluator extends AbstractExpressionEvaluator {

    private final HashMap<String, QLDataTypeWrapper> variablesRegister = new HashMap<>();

    public ASTExpressionEvaluator(List<Question> questions) {
        // default empty values for variables register
        for (Question question : questions) {
            this.variablesRegister.put(question.getVariableName(), QLDataTypeWrapper.createValue(question.getVariableType().toDataType(), ""));
        }
    }

    @Override
    public QLDataTypeWrapper visit(VariableReference variableReference) {
        return this.variablesRegister.get(variableReference.getName());
    }

}
