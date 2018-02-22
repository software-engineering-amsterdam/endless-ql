package org.uva.sea.ql.parser.nodeTypes;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.BaseVisitor;

public abstract class SingleNode extends ASTNode {
    private ASTNode value;

    public SingleNode(ASTNode value) {
        this.value = value;
    }

    public ASTNode getValue() {
        return value;
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }
}
