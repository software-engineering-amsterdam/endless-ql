package model;

import expression.Expression;

public class Condition {

    public final Expression expression;
    public final Block block;

    public Condition(Expression expression, Block block){
        this.expression = expression;
        this.block = block;
    }

}