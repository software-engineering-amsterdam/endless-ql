package ql.ast.statement;

import ql.ast.expression.Identifier;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.StatementVisitor;

public class AnswerableQuestion extends Question {

    public AnswerableQuestion(String label, Identifier id, Type type) {
        super(label, id, type);
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id.toString() + ": " + type.toString();
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
