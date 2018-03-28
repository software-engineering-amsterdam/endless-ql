package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

import java.util.List;

public class Section extends Statement {
    private final String title;
    private final List<Statement> statements;

    public Section(Token token, String title, List<Statement> statement) {
        super(token);
        this.title = title;
        this.statements = statement;
    }

    public String getTitle() {
        return title;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
