package org.uva.sea.ql.parser.nodeTypes;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Traverse;

public abstract class SingleNode extends ASTNode {
    private ASTNode value;

    public SingleNode(ASTNode value) {
        this.value = value;
    }

    public void traverseChildren(Traverse traverse) {
        this.value.traverse(traverse);
    }

    public ASTNode getValue() {
        return value;
    }

    public void setValue(ASTNode value) {
        this.value = value;
    }
}
