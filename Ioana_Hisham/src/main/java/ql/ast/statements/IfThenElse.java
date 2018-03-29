package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.visitors.StatementVisitor;

import java.util.List;

public class IfThenElse extends IfThen {
    private final List<Statement> elseStatements;

    public IfThenElse(int lineNumber, Expression condition, List<Statement> thenStatements, List<Statement> elseStatements) {
        super(lineNumber, condition, thenStatements);
        this.elseStatements = elseStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
