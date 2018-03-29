package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.ast.expressions.Identifier;
import ql.ast.expressions.literals.StringLiteral;
import ql.types.Type;
import ql.visitors.StatementVisitor;

public class CalculableQuestion extends Question {
    private  final Expression calculableValue;
    public CalculableQuestion(int lineNumber, StringLiteral description, Identifier identifier, Type type, Expression calculableValue) {
        super(lineNumber, description, identifier, type);
        this.calculableValue = calculableValue;
    }

    public Expression getCalculableValue() {
        return calculableValue;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
