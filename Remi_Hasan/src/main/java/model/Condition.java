package model;

import expression.Expression;

public class Condition extends BlockElement{

    public final Expression expression;
    public final Block block;

    public Condition(Expression expression, Block block){
        this.expression = expression;
        this.block = block;
    }

    @Override
    public boolean isQuestion() {
        return false;
    }

    @Override
    public boolean isCondition() {
        return true;
    }
}