package ParseObjects.Expressions;

public class IntegerConstant extends Constant<Integer> {
    public IntegerConstant(Integer value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Integer;
    }
}
