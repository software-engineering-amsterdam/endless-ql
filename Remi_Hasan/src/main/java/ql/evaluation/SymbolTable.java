package ql.evaluation;

import ql.QLBaseVisitor;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable extends QLBaseVisitor<Void> {
    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this);
    private Map<String, Expression> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void buildTable(Form form) {
        form.accept(this);
    }

    @Override
    public Void visit(Question question) {
        // Add form question to the symbol table
        if (question.computedAnswer != null) {
            table.put(question.identifier, question.computedAnswer);
        } else {
            // Not a computed question, so it is undefined until it is set by the user
            table.put(question.identifier, new ExpressionVariableUndefined(question.getToken(), question.type));
        }
        return super.visit(question);
    }

    public Expression getExpression(String identifier) {
        if (this.table.containsKey(identifier)) {
            return this.table.get(identifier);
        } else {
            throw new UnsupportedOperationException("Cannot get value for unknown field '" + identifier + "'.");
        }
    }

    public void setExpression(String identifier, Expression value) {
        System.out.println("setExpression[" + identifier + "][" + value + "]");
        this.table.put(identifier, value);
    }

    public Value getValue(String identifier){
        return expressionEvaluator.visit(getExpression(identifier));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\nCurrent form values are: \n");
        for(Map.Entry<String, Expression> entry : this.table.entrySet()){
            stringBuilder.append("\t");
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" => ");
            stringBuilder.append(getValue(entry.getKey()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
