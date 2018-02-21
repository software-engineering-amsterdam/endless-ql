package ParseObjects;

import ParseObjects.Expressions.Expression;

public class Condition {
    Expression condition;
    Block block;

    public Condition(Expression condition, Block block){
        this.block = block;
        this.condition = condition;
    }
}
