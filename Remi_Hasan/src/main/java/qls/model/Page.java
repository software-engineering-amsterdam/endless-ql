package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.statement.Statement;

import java.util.List;

public class Page extends QLSNode {

    private final String identifier;
    private final List<Statement> statements;

    public Page(Token token, String identifier, List<Statement> statements) {
        super(token);
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
