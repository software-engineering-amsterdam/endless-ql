package ql.ast.statement;

import ql.ast.expression.Identifier;
import ql.ast.type.Type;

public class AnswerableQuestion extends Question {

    public AnswerableQuestion(String label, Identifier id, Type type) {
        super(label, id, type);
    }

    @Override
    public String toString() {
        return "\"" + label.toString() + "\" " + id.toString() + ": " + type.toString();
    }
}
