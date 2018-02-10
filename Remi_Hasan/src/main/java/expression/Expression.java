package expression;

import model.Form;

public abstract class Expression<T> {

    public abstract T evaluate(Form form);
    public abstract boolean isEvaluable(Form form);

}
