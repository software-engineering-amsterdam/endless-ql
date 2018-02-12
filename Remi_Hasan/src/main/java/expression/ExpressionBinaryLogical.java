package expression;

import model.Form;

public abstract class ExpressionBinaryLogical extends Expression<Boolean> {
    Expression left;
    Expression right;

    @Override
    public boolean isBoolean(Form form){
        return true;
    }
}
