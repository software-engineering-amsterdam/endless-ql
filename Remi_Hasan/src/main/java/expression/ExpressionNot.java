package expression;

import model.Form;

public class ExpressionNot extends ExpressionUnary<Boolean> {

    public ExpressionNot(Expression v) {
        super(v, (form, a) -> v.evaluate(form).not().evaluate(form), "!");
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Boolean;
    }
}
//public class ExpressionNot extends Expression<Boolean> {
//
//    private final Expression value;
//
//    public ExpressionNot(Expression value){
//        this.value = value;
//    }
//
//    @Override
//    public ReturnType getReturnType(Form form) {
//        return ReturnType.Boolean;
//    }
//
//    @Override
//    public ExpressionVariable evaluate(Form form) {
//        return this.value.evaluate(form).not();
//    }
//}
