package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

public enum MoneyType {
    MoneyType_Euro ("€"),
    MoneyType_Dollar ("$");

    private final String name;

    MoneyType(String s) {
        this.name = s;
    }

    public boolean equalsName(String otherName) {
        return (this.name != null) && this.name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

    public static MoneyType fromString(String text) {
        for (MoneyType money : MoneyType.values()) {
            if (money.name.equalsIgnoreCase(text)) {
                return money;
            }
        }
        return null;
    }
}
