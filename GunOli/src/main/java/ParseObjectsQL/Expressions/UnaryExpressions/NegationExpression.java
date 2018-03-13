package ParseObjectsQL.Expressions.UnaryExpressions;

import ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import ParseObjectsQL.Expressions.EvaluationType;
import ParseObjectsQL.Expressions.Expression;
import ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

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
            return new UndefinedConstant();
        }

        if(this.getExpression().returnType() == EvaluationType.Integer){
            Integer exprValue = Integer.parseInt(this.getExpression().evaluate().getValue().toString());
            return new IntegerConstant(exprValue * -1);
        }

        Double exprValue = Double.parseDouble(this.getExpression().evaluate().getValue().toString());
        return new DecimalConstant(exprValue * -1);
    }
}
