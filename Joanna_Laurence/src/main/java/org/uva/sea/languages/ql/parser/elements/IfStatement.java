package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class IfStatement extends Statement {

    private final Expression expression;

    private final Statements thenBlock;
    private final Statements otherwiseBlock;

    public IfStatement(Token token, Expression expression, Statements thenBlock, Statements otherwiseBlock) {
        super(token);
        this.thenBlock = thenBlock;
        this.expression = expression;
        this.otherwiseBlock = otherwiseBlock;
    }

    public ASTNode getExpression() {
        return this.expression;
    }

    public Statements getThenBlock() {
        return this.thenBlock;
    }

    public Statements getOtherwiseBlock() {
        return this.otherwiseBlock;
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
