package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Bool extends ASTNode  {

    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean isTrue() {
        return value;
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("boolean");
    }
}
