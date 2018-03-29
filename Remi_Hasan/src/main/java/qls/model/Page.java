package qls.model;

import qls.model.statement.Statement;
import qls.visitor.IQLSVisitor;

import java.util.List;

public class Page extends QLSNode {

    private final String identifier;
    private final List<Statement> statements;

    public Page(String identifier, List<Statement> statements) {
        this.identifier = identifier;
        this.statements = statements;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
