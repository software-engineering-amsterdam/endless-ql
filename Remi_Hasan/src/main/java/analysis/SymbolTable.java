package analysis;

import expression.Expression;
import expression.ReturnType;
import expression.variable.ExpressionVariable;
import expression.variable.ExpressionVariableBoolean;
import expression.variable.ExpressionVariableNumber;
import expression.variable.ExpressionVariableString;
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

    public String getStringValue(String identifier, ReturnType type) {
        ExpressionVariable evaluated = this.getExpression(identifier);

        // Value might not yet have been set by the user, so return empty string
        if(evaluated.getReturnType(this) == ReturnType.UNDEFINED) {
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

    public void setValue(String identifier, String value, ReturnType type) {
        switch(type) {
            case INTEGER:
            case DECIMAL:
            case MONEY:
                this.table.put(identifier, new ExpressionVariableNumber(value));
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
