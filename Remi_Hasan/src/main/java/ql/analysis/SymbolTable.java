package ql.analysis;

import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Expression> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void buildTable(Form form) {
        for (Question question : form.questions) {
            if(question.isComputed()) {
                table.put(question.name, question.defaultAnswer);
            } else {
                table.put(question.name, new ExpressionVariableUndefined(question.getToken(), question.type));
            }
        }
    }

    public Expression getExpression(String identifier) {
        if (this.table.containsKey(identifier)) {
            return this.table.get(identifier);
        } else {
            throw new UnsupportedOperationException("Cannot get value for unknown field '" + identifier + "'.");
        }
    }

    // TODO: move to value?
    public String getStringValue(String identifier, ReturnType type) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this);
        Value evaluated = expressionEvaluator.visit(table.get(identifier));

        // Undefined values should display nothing
        if (evaluated.isUndefined()) {
            return "";
        }

        switch (type) {
            case INTEGER:
                return evaluated.getIntValue().toString();
            case DECIMAL:
                return evaluated.getDecimalValue().toString();
            case MONEY:
                return evaluated.getMoneyValue().toString();
            case STRING:
                return evaluated.getStringValue();
            case BOOLEAN:
                return evaluated.getBooleanValue().toString();
            case DATE:
                return new SimpleDateFormat("dd-MM-yyy").format(evaluated.getDateValue());
            default:
                return "";
        }
    }

    public Map<String, Expression> getAllAnswers(){
        return table;
    }

    public void setExpression(String identifier, Expression value) {
        this.table.put(identifier, value);
    }
}
