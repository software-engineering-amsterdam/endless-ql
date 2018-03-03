package ParseObjects.Expressions.UnaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

public class NegationExpression extends UnaryExpression {
    public NegationExpression(Expression expr){
        super("-", expr);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Constant evaluate(){
        if(!this.getExpression().isArithmetic()){
            //Todo: Implement either exception throw or error statement with undefined value.
        }

        if(this.getExpression().returnType() == EvaluationType.Integer){
            Integer exprValue = Integer.parseInt(this.getExpression().evaluate().getValue().toString());
            return new IntegerConstant(exprValue * -1);
        }

        Double exprValue = Double.parseDouble(this.getExpression().evaluate().getValue().toString());
        return new DecimalConstant(exprValue * -1);
    }
}
