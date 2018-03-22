package ql.ast.statement;

import ql.ast.expression.Identifier;
import ql.visitors.interfaces.StatementVisitor;

public class AnswerableQuestion extends Question {

    public AnswerableQuestion(String label, Identifier id) {
        super(label, id);
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id + ": " + id.getType();
    }
    
    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public boolean isAnswerable() {
        return true;
    }
}
