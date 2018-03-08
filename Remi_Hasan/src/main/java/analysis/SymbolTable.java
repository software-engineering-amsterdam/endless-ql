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
    public Map<String, Expression> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public SymbolTable(Form form) {
        this.table = new HashMap<>();

        for(Question question : form.questions) {
            table.put(question.name, question.defaultAnswer);
        }
    }

    public boolean containsExpression(String identifier){
        return this.table.containsKey(identifier);
    }

    public Expression getExpression(String identifier) {
        if(this.table.containsKey(identifier)){
            return this.table.get(identifier);
        } else {
            throw new UnsupportedOperationException("Cannot get value for unknown field '" + identifier + "'.");
        }
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

    public void setExpression(String identifier, Expression value) {
        this.table.put(identifier, value);
    }
}
