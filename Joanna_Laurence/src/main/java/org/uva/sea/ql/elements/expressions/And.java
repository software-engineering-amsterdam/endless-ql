package org.uva.sea.ql.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.elements.ASTNode;
import org.uva.sea.ql.elements.types.Type;
import org.uva.sea.ql.nodeTypes.BinaryOperator;
import org.uva.sea.ql.NodeType;
import org.uva.sea.visitor.IASTVisitor;

public class And extends BinaryOperator {
    public And(Token token, ASTNode leftHandSide, ASTNode rightHandSide) {
        super(token, leftHandSide, rightHandSide);
    }

    /**
     * @return True or false is returned
     */
    public Type getType() {
        return new Type(NodeType.BOOLEAN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
