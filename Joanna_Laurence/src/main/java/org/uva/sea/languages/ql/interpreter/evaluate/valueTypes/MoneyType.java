package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

public enum MoneyType {
    MoneyType_Euro("€"),
    MoneyType_Dollar("$");

    private final String name;

    MoneyType(final String s) {
        this.name = s;
    }

    public static MoneyType fromString(final String text) {
        for (final MoneyType money : MoneyType.values()) {
            if (money.name.equalsIgnoreCase(text)) {
                return money;
            }
        }
        return null;
    }

    public boolean equalsName(final String otherName) {
        return (this.name != null) && this.name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
