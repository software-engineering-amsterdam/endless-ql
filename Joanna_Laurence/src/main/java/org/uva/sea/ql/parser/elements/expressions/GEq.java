package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class GEq extends DualNode {
    public GEq(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    public void traverse(Traverse traverse) {
        super.traverse(traverse);
        traverse.doLogical(this);
        traverse.doGEq(this);
        this.traverseChildren(traverse);
    }

    /**
     * @return True or false is returned
     */
    public Type getType() {
        return new Type("Boolean");
    }
}
