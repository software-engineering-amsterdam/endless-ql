package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.ql.traverse.Traverse;

public class Pos extends SingleNode {

    public Pos(ASTNode value) {
        super(value);
    }


    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doPos(this);
    }

    public Type getType() {
        return this.getValue().getType();
    }
}
