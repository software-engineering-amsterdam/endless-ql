package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class Add extends DualNode {
    public Add(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        super.traverseNode(traverse, traverseType);
        traverse.doOperation(this);
        traverse.doAdd(this);

    }

    /**
     * The value is returned, so the type of LHS
     * @return The type
     */
    public Type getType() {
        return this.getLhs().getType();
    }
}
