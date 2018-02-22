package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Or extends DualNode  {
    public Or(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("boolean");
    }
}
