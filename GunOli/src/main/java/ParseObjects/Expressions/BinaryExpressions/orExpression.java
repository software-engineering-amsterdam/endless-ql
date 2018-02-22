package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.*;

public class orExpression extends BinaryExpression<Boolean> {

    public orExpression(Expression left, Expression right){
        super("||", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return left.or(right);
    }

    @Override
    public Boolean isLogical(){return true;}
}
