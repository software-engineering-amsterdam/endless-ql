package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.parser.NodeType;

public class SpecificationKey {
    private final Class operator;
    private final NodeType leftType;
    private final NodeType rightType;

    public SpecificationKey(Class operator, NodeType leftType, NodeType rightType) {
        this.operator = operator;
        this.leftType = leftType;
        this.rightType = rightType;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof SpecificationKey)) {
            SpecificationKey key = (SpecificationKey) obj;
            return this.operator.equals(key.operator) && this.leftType == key.leftType && this.rightType == key.rightType;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.operator.hashCode() ^ this.leftType.hashCode() ^ this.rightType.hashCode();
    }
}