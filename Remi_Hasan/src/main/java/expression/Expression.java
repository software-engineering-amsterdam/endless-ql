package expression;

import expression.variable.ExpressionVariable;

public abstract class Expression {

    public abstract ReturnType getReturnType();

    public abstract ExpressionVariable evaluate();

    public boolean isSettable() {
        return false;
    }

    public void setValue(String value) {
        throw new UnsupportedOperationException("Cannot set value to non-variable expression");
    }

    public abstract void typeCheck();

    @Override
    public boolean equals(Object other){
        return this.toString().equals(other.toString());
    }
}