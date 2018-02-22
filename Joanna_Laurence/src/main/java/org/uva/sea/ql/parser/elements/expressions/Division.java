package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Division extends DualNode  {
    public Division(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * The value is returned, so the type of LHS
     * @return The type
     */
    public Type getType() {
        return this.getLhs().getType();
    }
}
