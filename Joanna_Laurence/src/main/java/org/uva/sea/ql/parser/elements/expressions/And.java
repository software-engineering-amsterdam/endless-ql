package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.traverse.Visitor;

public class And extends BinaryOperator {
    public And(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    /**
     * @return True or false is returned
     */
    public Type getType() {
        return new Type(NodeType.BOOLEAN);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
