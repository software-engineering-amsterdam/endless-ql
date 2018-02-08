package model;

import expression.Expression;

public class Condition {

    private final Expression expression;
    private final Block block;

    public Condition(Expression expression, Block block){
        this.expression = expression;
        this.block = block;
    }

}
