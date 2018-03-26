package org.uva.sea.languages.ql.parser;

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

    private boolean isBasicNumber() {
        return (this == NodeType.INTEGER) || (this == NodeType.DECIMAL);
    }

    public boolean isTypeCompatible(NodeType type) {
        boolean exactlyTheSame = this == type;
        boolean compatibleTypes = (this == NodeType.DECIMAL) && type.isBasicNumber();
        return (exactlyTheSame || compatibleTypes);
    }
}
