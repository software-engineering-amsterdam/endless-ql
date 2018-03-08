package expression;

public enum ReturnType {
    INTEGER, DECIMAL, MONEY, NUMBER, BOOLEAN, STRING, DATE, UNDEFINED;

    public Boolean eq(ReturnType other) {
        return this.equals(other);
    }

    public Boolean sum(ReturnType other) {
        return this.isNumber() && other.isNumber();
    }

    public Boolean subtract(ReturnType other) {
        return sum(other);
    }

    public Boolean divide(ReturnType other) {
        return this.isNumber() && other.isNumber();
    }

    public Boolean multiply(ReturnType other) {
        return divide(other);
    }

    public Boolean ge(ReturnType other) {
        return (this.isNumber() && other.isNumber()) || (this == DATE && other == DATE);
    }

    public Boolean gt(ReturnType other) {
        return ge(other);
    }

    public Boolean le(ReturnType other) {
        return ge(other);
    }

    public Boolean lt(ReturnType other) {
        return ge(other);
    }

    public Boolean and(ReturnType other) {
        return this == BOOLEAN && other == BOOLEAN;
    }

    public Boolean or(ReturnType other) {
        return this == BOOLEAN && other == BOOLEAN;
    }

    public Boolean neg() {
        return this.isNumber();
    }

    public Boolean not() {
        return this == BOOLEAN;
    }

    public Boolean isNumber() {
        // TODO: do we still need NUMBER?
        return this == NUMBER || this == INTEGER || this == DECIMAL || this == MONEY;
    }
}
