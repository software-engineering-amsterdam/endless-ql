package analysis;

import astvisitor.*;
import expression.Expression;
import expression.ReturnType;
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

    public void printValues() {
        for (Map.Entry<String, Expression> entry : table.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public Expression getExpression(String identifier) {
        return this.table.get(identifier);
    }

    // TODO: move to value?
    public String getStringValue(String identifier, ReturnType type) {
        InterpreterVisitor interpreterVisitor = new InterpreterVisitor(this);
        Value evaluated = interpreterVisitor.visit(table.get(identifier));

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
                this.table.put(identifier, new ExpressionVariableInteger(Integer.parseInt(value)));
                break;
            case DECIMAL:
                this.table.put(identifier, new ExpressionVariableDecimal(Double.parseDouble(value)));
                break;
            case MONEY:
                this.table.put(identifier, new ExpressionVariableMoney(value));
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
