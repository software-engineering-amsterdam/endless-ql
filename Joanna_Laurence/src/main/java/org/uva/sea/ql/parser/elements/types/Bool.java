package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class Bool extends DualNode {
    public Bool(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    public void traverse(Traverse traverse) {
        traverse.doBool(this);
        this.traverseChildren(traverse);
    }
}
