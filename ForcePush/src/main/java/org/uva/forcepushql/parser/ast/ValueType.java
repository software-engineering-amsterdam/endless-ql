package org.uva.forcepushql.parser.ast;

import org.antlr.v4.runtime.tree.TerminalNode;

public enum ValueType {
    UNKNOWN,
    MONEY,
    BOOL,
    STR,
    INT,
    DECIMAL;

    private boolean isBasicNumber() {
        return (this == ValueType.INT) || (this == ValueType.DECIMAL);
    }

    public boolean isTypeCompatible(ValueType type) {
        boolean exactlyTheSame = this == type;
        boolean compatibleTypes = (this == ValueType.DECIMAL) && type.isBasicNumber();
        return (exactlyTheSame || compatibleTypes);
    }

    public static ValueType valueOfString(String string){

        switch (string){

            case "money": return MONEY;
            case "boolean": return BOOL;
            case "string": return STR;
            case "integer": return INT;
            case "decimal": return DECIMAL;
            default: return UNKNOWN;
        }
    }
}