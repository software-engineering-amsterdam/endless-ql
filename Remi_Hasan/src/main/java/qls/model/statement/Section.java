package qls.model.statement;

import qls.visitor.IQLSVisitor;

import java.util.List;

public class Section extends Statement {
    private final String title;
    private final List<Statement> statements;

    public Section(String title, List<Statement> statement) {
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
