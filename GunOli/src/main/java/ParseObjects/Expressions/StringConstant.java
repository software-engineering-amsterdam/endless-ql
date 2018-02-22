package ParseObjects.Expressions;

public class StringConstant extends Constant<String> {
    public StringConstant(String value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.String;
    }

}
