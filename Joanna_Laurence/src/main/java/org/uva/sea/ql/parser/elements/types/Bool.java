package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class Bool extends ASTNode {

    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean isTrue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doBool(this);
    }

    public Type getType() {
        return new Type("boolean");
    }
}
