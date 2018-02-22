package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.BaseVisitor;

public class And extends DualNode  {
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
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }
}
