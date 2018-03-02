package org.uva.sea.ql.DataObject;

import org.uva.sea.ql.parser.NodeType;

public class SpecificationKey {
    private Class operator;
    private NodeType leftType;
    private NodeType rightType;

    public SpecificationKey(Class operator, NodeType leftType, NodeType rightType) {
        this.operator = operator;
        this.leftType = leftType;
        this.rightType = rightType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof SpecificationKey) {
            SpecificationKey key = (SpecificationKey) obj;
            return operator.equals(key.operator) && leftType.equals(key.leftType) && rightType.equals(key.rightType);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return operator.hashCode() ^ leftType.hashCode() ^ rightType.hashCode();
    }
}