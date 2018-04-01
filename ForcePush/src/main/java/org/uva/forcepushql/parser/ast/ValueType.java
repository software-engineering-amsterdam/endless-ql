package org.uva.forcepushql.parser.ast;

public enum ValueType {
    UNKNOWN,
    MONEY,
    BOOLEAN,
    STRING,
    INTEGER,
    DECIMAL;

    public static ValueType valueOfString(String string)
    {
        switch (string)
        {
            case "money": return MONEY;
            case "boolean": return BOOLEAN;
            case "string": return STRING;
            case "integer": return INTEGER;
            case "decimal": return DECIMAL;
            default: return UNKNOWN;
        }
    }

}