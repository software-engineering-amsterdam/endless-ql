package analysis;

import expression.Expression;
import expression.ExpressionVariable;
import expression.variable.*;
import model.Form;
import model.Question;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Expression> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public SymbolTable(Form form) {
        this.table = new HashMap<>();
        for(Question question : form.questions) {
            table.put(question.name, question.defaultAnswer);
        }
    }

    public ExpressionVariable getExpression(String identifier) {
        return table.get(identifier).evaluate(this);
    }

    // TODO: move to value?
    public String getStringValue(String identifier, ReturnType type) {
        ExpressionVariable evaluated = this.getExpression(identifier).evaluate(this);

        // Undefined values should display nothing
        if(evaluated.isUndefined()) {
            return "";
        }

        switch(type) {
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
            default:
                return "";
        }
    }

    // TODO: fails when number value is '-'
    public void setValue(String identifier, String value, ReturnType type) {
        if(value.isEmpty()) {
            this.table.put(identifier, new ExpressionVariableUndefined(type));
            return;
        }

        switch(type) {
            case INTEGER:
            case DECIMAL:
            case MONEY:
                this.table.put(identifier, new ExpressionVariableInteger(value));
                break;
            case STRING:
                this.table.put(identifier, new ExpressionVariableString(value));
                break;
            case BOOLEAN:
                this.table.put(identifier, new ExpressionVariableBoolean(Boolean.valueOf(value)));
                break;
        }
    }
}
