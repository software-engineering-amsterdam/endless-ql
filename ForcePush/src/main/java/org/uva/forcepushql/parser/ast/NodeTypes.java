package org.uva.forcepushql.parser.ast;

public enum NodeTypes {
    INVALID,
    UNKNOWN,
    MONEY,
    BOOL,
    STR,
    INT,
    DECIMAL;

    private boolean isBasicNumber() {
        return (this == NodeTypes.INT) || (this == NodeTypes.DECIMAL);
    }

    public boolean isTypeCompatible(NodeTypes type) {
        boolean exactlyTheSame = this == type;
        boolean compatibleTypes = (this == NodeTypes.DECIMAL) && type.isBasicNumber();
        return (exactlyTheSame || compatibleTypes);
    }
}