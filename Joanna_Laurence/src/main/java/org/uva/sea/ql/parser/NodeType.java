package org.uva.sea.ql.parser;

public enum NodeType {
    INVALID,
    UNKNOWN,
    MONEY,
    BOOLEAN,
    STRING,
    INTEGER,
    DATE,
    DECIMAL;

    public boolean isBasicNumber() {
        return this == INTEGER || this == DECIMAL;
    }

    public boolean isNumber() {
        return this == INTEGER || this == DECIMAL || this == MONEY;
    }

    public boolean isTypeCompatible(NodeType type) {
        boolean exactlyTheSame = this.equals(type);
        boolean compatibleTypes = this == DECIMAL && type.isBasicNumber();
        return (exactlyTheSame || compatibleTypes);
    }
}
