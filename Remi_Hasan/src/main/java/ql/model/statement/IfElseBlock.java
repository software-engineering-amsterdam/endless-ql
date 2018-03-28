package ql.model.statement;

import org.antlr.v4.runtime.Token;
import ql.IQLVisitor;
import ql.model.expression.Expression;

import java.util.List;

public class IfElseBlock extends IfBlock {
    private final List<Statement> falseStatements;

    public IfElseBlock(Token start, Expression condition, List<Statement> trueStatements, List<Statement> falseStatements) {
        super(start, condition, trueStatements);
        this.falseStatements = falseStatements;
    }

    public List<Statement> getFalseStatements() {
        return falseStatements;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
