package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Positive extends SingleNode  {

    public Positive(ASTNode value) {
        super(value);
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return this.getValue().getType();
    }
}
