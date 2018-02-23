package org.uva.sea.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.traverse.Visitor;

public class Division extends BinaryOperator {
    public Division(Token token, ASTNode lhs, ASTNode rhs) {
        super(token, lhs, rhs);
    }

    /**
     * The value is returned, so the type of LHS
     * @return The type
     */
    public Type getType() {
        return super.getLhs().getType();
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
