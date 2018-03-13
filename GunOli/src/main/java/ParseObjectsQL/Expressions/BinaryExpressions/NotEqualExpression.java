package ParseObjectsQL.Expressions.BinaryExpressions;

import ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import ParseObjectsQL.Expressions.EvaluationType;
import ParseObjectsQL.Expressions.Expression;
import ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;

public class NotEqualExpression extends BinaryExpression {
    public NotEqualExpression(Expression left, Expression right){
        super("!=",left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return new BooleanConstant(!left.getValue().equals(right.getValue()));
    }

    @Override
    public Boolean isLogical(){return true;}
}
