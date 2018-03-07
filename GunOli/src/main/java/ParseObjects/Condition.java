package ParseObjects;

import ParseObjects.Expressions.Expression;

public class Condition {
    Expression condition;
    Block block;

    public Condition(Expression condition, Block block){
        setBlock(block);
        setCondition(condition);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
