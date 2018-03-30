package ql.model.statement;

import ql.model.expression.Expression;
import ql.visitor.IQLVisitor;

import java.util.List;

public class IfBlock extends Statement {
    private final Expression condition;
    private final List<Statement> trueStatements;

    public IfBlock(Expression condition, List<Statement> trueStatements) {
        this.condition = condition;
        this.trueStatements = trueStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getTrueStatements() {
        return trueStatements;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
