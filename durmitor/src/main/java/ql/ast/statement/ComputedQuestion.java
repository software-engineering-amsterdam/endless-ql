package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.type.Type;

public class ComputedQuestion extends Question {
    
    private Expression expr;

    public ComputedQuestion(String label, Identifier id, Type type, Expression expr) {
        super(label, id, type);
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id.toString() + ": " + type.toString() + "( " + expr.toString() + " )";
    }
}
