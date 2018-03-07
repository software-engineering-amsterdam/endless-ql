package astvisitor;

import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;

public interface IASTVisitor {
    NumValue visit(ExpressionArithmeticDivide e);
    NumValue visit(ExpressionArithmeticMultiply e);
    NumValue visit(ExpressionArithmeticSubtract e);
    NumValue visit(ExpressionArithmeticSum e);

    BoolValue visit(ExpressionComparisonEq e);
    BoolValue visit(ExpressionComparisonGE e);
    BoolValue visit(ExpressionComparisonGT e);
    BoolValue visit(ExpressionComparisonLE e);
    BoolValue visit(ExpressionComparisonLT e);

    BoolValue visit(ExpressionLogicalAnd e);
    BoolValue visit(ExpressionLogicalOr e);


    BoolValue visit(ExpressionUnaryNot e);
    NumValue visit(ExpressionUnaryNeg e);

    BoolValue visit(ExpressionVariableBoolean e);
    DateValue visit(ExpressionVariableDate e);
    NumValue visit(ExpressionVariableNumber e);
    StringValue visit(ExpressionVariableString e);

    // TODO remove?
//    T visit(ExpressionVariableUndefined e);
}