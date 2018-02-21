package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.StatementVisitor;

public class ComputedQuestion extends Question implements ExpressionVisitable {
    
    private Expression expr;

    public ComputedQuestion(String label, Identifier id, Type type, Expression expr) {
        super(label, id, type);
        this.expr = expr;
    }

    public Expression getExpression() {
        return expr;
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id.toString() + ": " + type.toString() + "( " + expr.toString() + " )";
    }
    
    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
