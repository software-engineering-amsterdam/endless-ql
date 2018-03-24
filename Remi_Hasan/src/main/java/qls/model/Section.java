package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

import javax.swing.plaf.nimbus.State;
import java.util.List;

public class Section extends Statement {
    public final String identifier;
    private final List<Statement> statements;

    public Section(Token token, String identifier, List<Statement> statement) {
        super(token);
        this.identifier = identifier;
        this.statements = statement;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
