package ParseObjects.Expressions;

class DecimalConstant extends Constant<Double> {
    public DecimalConstant(Double value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }
}
