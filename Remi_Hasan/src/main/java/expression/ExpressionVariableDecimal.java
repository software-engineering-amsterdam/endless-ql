package expression;

import model.Form;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    public ExpressionVariableDecimal(Double value){
        this.value = value;
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }

    @Override
    public void setValue(String value){
        this.value = Double.parseDouble(value);
    }


    // TODO
    @Override
    public Double divide(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            double left = (double)other.evaluate(form);
            double right = (double)other.evaluate(form);
            if(right != 0.0){
                return left / right;
            }
        }
        return null;
    }
    @Override
    public Double multiply(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value * (double)other.evaluate(form);
        }
        return null;
    }

    @Override
    public Double subtract(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value - (double)other.evaluate(form);
        }
        return null;
    }

    @Override
    public Double sum(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value + (double)other.evaluate(form);
        }
        return null;
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value >= (double)other.evaluate(form);
        }
        return null;
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value > (double)other.evaluate(form);
        }
        return null;
    }

    @Override
    public Boolean le(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value <= (double)other.evaluate(form);
        }
        return null;
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        if(this.getReturnType(form) == ReturnType.Number && other.getReturnType(form) == ReturnType.Number){
            return this.value < (double)other.evaluate(form);
        }
        return null;
    }

}
