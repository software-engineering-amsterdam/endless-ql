package ParseObjects.Expressions;

class BooleanConstant extends Constant<Boolean> {
    public BooleanConstant(Boolean value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }

    public Boolean evaluate(){
        return false;//Change
    }
}
