package ql.evaluation;

import ql.QLVisitor;
import ql.model.Form;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.expression.variable.ExpressionVariableUndefined;
import ql.model.statement.Question;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Expression> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void buildTable(Form form) {
        form.accept(new QLVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                // Add form question to the symbol table
                if (question.isComputed()) {
                    table.put(question.getIdentifier(), question.getComputedAnswer());
                } else if (question.getType().equals(ReturnType.BOOLEAN)) {
                    table.put(question.getIdentifier(), new ExpressionVariableBoolean(false));
                } else {
                    // Not a computed question, so it is undefined until it is set by the user
                    Expression expression = new ExpressionVariableUndefined(question.getType());
                    expression.setToken(question.getToken());
                    table.put(question.getIdentifier(), expression);
                }
                return super.visit(question);
            }
        });
    }

    public Expression getExpression(String identifier) {
        if (this.table.containsKey(identifier)) {
            return this.table.get(identifier);
        } else {
            throw new IllegalArgumentException("Cannot get value for unknown field '" + identifier + "'.");
        }
    }

    public void setExpression(String identifier, Expression value) {
        this.table.put(identifier, value);
    }
}
