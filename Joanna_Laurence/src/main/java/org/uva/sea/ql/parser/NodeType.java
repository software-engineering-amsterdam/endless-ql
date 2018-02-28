package org.uva.sea.ql.parser;

public enum NodeType {
    UNKNOWN,
    MONEY,
    BOOLEAN,
    STRING,
    INTEGER,
    DATE,
    DECIMAL;

    public boolean isNumber() {
        return this == INTEGER || this == DECIMAL || this == MONEY;
    }

    public boolean isBasicNumber() {
        return this == INTEGER || this == DECIMAL;
    }

    public boolean isTypeCompatible(NodeType type) {
        boolean exactlyTheSame = this.equals(type);
        boolean compatibleTypes = this.isBasicNumber() && type.isBasicNumber();
        return (exactlyTheSame || compatibleTypes);
    }
}
