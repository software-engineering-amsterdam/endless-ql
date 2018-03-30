package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.visitors.StatementVisitor;

import java.util.List;

public class IfThen extends Statement {
    private final Expression condition;
    private final List<Statement> thenStatements;
    public IfThen(int lineNumber, Expression condition, List<Statement> thenStatements) {
        super(lineNumber);
        this.condition = condition;
        this.thenStatements = thenStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getThenStatements() {
        return thenStatements;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
