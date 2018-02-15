package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class Bool extends ASTNode {

    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void traverse(Traverse traverse) {
        traverse.doBool(this);
    }

    public Type getType() {
        return new Type("boolean");
    }
}
