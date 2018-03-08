package org.uva.sea.ql.parser;

public enum NodeType {
    INVALID,
    UNKNOWN,
    MONEY_EURO,
    MONEY_DOLLAR,
    BOOLEAN,
    STRING,
    INTEGER,
    DATE,
    DECIMAL;

    public boolean isBasicNumber() {
        return this == INTEGER || this == DECIMAL;
    }

    public boolean isTypeCompatible(NodeType type) {
        boolean exactlyTheSame = this.equals(type);
        boolean compatibleTypes = this == DECIMAL && type.isBasicNumber();
        return (exactlyTheSame || compatibleTypes);
    }
}
