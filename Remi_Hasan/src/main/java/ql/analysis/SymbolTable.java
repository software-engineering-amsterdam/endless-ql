package ql.analysis;

import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Expression> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void buildTable(Form form) {
        for (Question question : form.questions) {
            if (question.isComputed()) {
                table.put(question.identifier, question.computedAnswer);
            } else {
                table.put(question.identifier, new ExpressionVariableUndefined(question.getToken(), question.type));
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

    public void setExpression(String identifier, Expression value) {
        this.table.put(identifier, value);

        // TODO remove debugging info below
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this);
        for (Map.Entry<String, Expression> entry : table.entrySet()) {
            Value evaluatedValue = expressionEvaluator.visit(entry.getValue());
            System.out.println(entry.getKey() + " " + evaluatedValue.toString());
        }
    }
}
