package ql.model.expression;

import java.util.List;

public enum ReturnType {
    INTEGER, DECIMAL, MONEY, BOOLEAN, STRING, DATE;

    // Decimal strongest, integer weakest
    private static List numberPriorities = List.of(INTEGER, MONEY, DECIMAL);

    public Boolean isNumber() {
        return this == INTEGER || this == DECIMAL || this == MONEY;
    }

    // Returns the strongest number type, such that we can determine the type of an arithmetic expression
    // e.g. 3 + 5.0 becomes decimal, 5.0 + 3.25 becomes money
    public ReturnType getStrongestNumber(ReturnType other) {
        if (numberPriorities.indexOf(this) > numberPriorities.indexOf(other)) {
            return this;
        }
        return other;
    }

    // Check if THIS can be assigned to variable of OTHER
    public Boolean canBeAssignedTo(ReturnType questionType) {
        if (this.isNumber() && questionType.isNumber()) {
            // If assigned type is weaker than question type, types are compatible
            return questionType.getStrongestNumber(this) == questionType;
        }
        return this == questionType;
    }

    // Check if operations can be applied on two types
    // Using functions, as this way they can easily be extended
    public Boolean isCompatibleEquality(ReturnType other) {
        return this == other || this.isNumber() && other.isNumber();
    }

    public Boolean isCompatibleArithmetic(ReturnType other) {
        return this.isNumber() && other.isNumber();
    }

    public Boolean isCompatibleComparison(ReturnType other) {
        return this.isNumber() && other.isNumber();
    }
}
