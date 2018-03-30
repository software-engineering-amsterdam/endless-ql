package QL.Analysis;

public enum EvaluationType {
    Boolean, String, Integer, Decimal, Money, Date, Undefined;

    public Boolean isArithmetic(){
        return this == Integer || this == Decimal || this == Money;
    }

    public Boolean isLogical(){
        return this == Boolean;
    }
}
