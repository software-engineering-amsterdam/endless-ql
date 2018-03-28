package ql.model.statement;

import org.antlr.v4.runtime.Token;
import ql.IQLVisitor;
import ql.model.expression.Expression;

import java.util.List;

public class IfBlock extends Statement {
    private final Expression condition;
    private final List<Statement> trueStatements;

    public IfBlock(Token start, Expression condition, List<Statement> trueStatements) {
        super(start);
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
