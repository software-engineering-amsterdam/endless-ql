package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.ql.traverse.Visitor;

public class Not extends SingleNode  {
    public Not(ASTNode value) {
        super(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("boolean");
    }
}

