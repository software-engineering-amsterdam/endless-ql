package ql.logic.evaluators;

import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.logic.type.QLDataType;

import java.util.HashMap;
import java.util.List;

public class ASTExpressionEvaluator extends AbstractExpressionEvaluator {

    private HashMap<String, QLDataType> variablesRegister = new HashMap<>();

    public ASTExpressionEvaluator(List<Question> questions) {
        // default empty values for variables register
        for (Question question : questions) {
            this.variablesRegister.put(question.getVariableName(), QLDataType.createValue(question.getVariableType().toDataType(), ""));
        }
    }

    @Override
    public QLDataType visit(VariableReference variableReference) {
        return this.variablesRegister.get(variableReference.getName());
    }

}
