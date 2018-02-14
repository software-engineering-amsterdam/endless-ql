package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class Pos extends DualNode {
    public Pos(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    public void traverse(Traverse traverse) {
        super.traverse(traverse);
        traverse.doPos(this);
        this.traverseChildren(traverse);
    }

    public Type getType() {
        return this.getLhs().getType();
    }
}
