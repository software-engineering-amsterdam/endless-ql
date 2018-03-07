package expression.binary;

import astvisitor.NumValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionArithmeticDivide extends ExpressionBinary<Number> {

    ExpressionArithmeticDivide(Expression left, Expression right){
        super(left, right);
    }

    @Override
    public NumValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }

//    @Override
//    public NumValue accept(BaseASTVisitor visitor) {
//        return null;
//    }
}
