package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Addition extends DualNode  {
    public Addition(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    /**
     * The value is returned, so the type of LHS
     * @return The type
     */
    public Type getType() {
        return this.getLhs().getType(); //TODO return the type, not the node!
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }
}
