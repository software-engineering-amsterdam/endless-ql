public class Condition {

    private final Expression expression;
    private final Block block;

    Condition(Expression expression, Block block){
        this.expression = expression;
        this.block = block;
    }

}
