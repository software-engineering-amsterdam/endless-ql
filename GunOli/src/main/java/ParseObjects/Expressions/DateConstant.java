package ParseObjects.Expressions;

public class DateConstant extends Constant<String> {
    public DateConstant(String value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Date;
    }

    public String getDate(){
        return this.getValue();
    }
}
