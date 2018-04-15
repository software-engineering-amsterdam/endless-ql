package QL.Evaluation;

public enum EvaluationType {
    Boolean, String, Integer, Decimal, Money, Date, Undefined;

    public Boolean isArithmetic(){
        return this == Integer || this == Decimal || this == Money;
    }

    public Boolean isLogical(){
        return this == Boolean;
    }

    public EvaluationType hasPriority(EvaluationType evaluationType){
        if(this.isArithmetic() && evaluationType.isArithmetic()){
            if(this == Decimal || this == Money){
                return this;
            } else if (this == Integer && evaluationType == Integer){
                return this;
            }

            return evaluationType;
        }

        throw new IllegalArgumentException("Cannot compare priorities of non-numeric types");
    }
}
