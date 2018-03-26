package org.uva.sea.languages.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Type;
import org.uva.sea.languages.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class And extends BinaryOperator {
    public And(final Token token, final Expression leftHandSide, final Expression rightHandSide) {
        super(token, leftHandSide, rightHandSide);
    }

    public final Type getType() {
        return new Type(NodeType.BOOLEAN);
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
