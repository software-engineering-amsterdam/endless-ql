package ql.evaluation;

import ql.model.expression.Expression;

public class Binding{
    public String name;
    public Expression expression;

    public Binding(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return "<" + name + "," + expression.toString() + ">";
    }
}
