package ql;

import ql.model.*;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Question;
import ql.model.statement.Statement;

public interface IQLVisitor<T> {
    T visit(Form form);

    // Statements
    T visit(Statement statement);

    T visit(IfBlock ifBlock);

    T visit(IfElseBlock ifElseBlock);

    T visit(Question question);

    // Expressions
    T visit(Expression expression);

    T visit(ExpressionArithmeticDivide expression);

    T visit(ExpressionArithmeticMultiply expression);

    T visit(ExpressionArithmeticSubtract expression);

    T visit(ExpressionArithmeticSum expression);

    T visit(ExpressionComparisonEq expression);

    T visit(ExpressionComparisonGE expression);

    T visit(ExpressionComparisonGT expression);

    T visit(ExpressionComparisonLE expression);

    T visit(ExpressionComparisonLT expression);

    T visit(ExpressionLogicalAnd expression);

    T visit(ExpressionLogicalOr expression);

    T visit(ExpressionUnaryNot expression);

    T visit(ExpressionUnaryNeg expression);

    T visit(ExpressionVariableBoolean expression);

    T visit(ExpressionVariableDate expression);

    T visit(ExpressionVariableInteger expression);

    T visit(ExpressionVariableDecimal expression);

    T visit(ExpressionVariableMoney expression);

    T visit(ExpressionVariableString expression);

    T visit(ExpressionVariableUndefined expression);

    T visit(ExpressionIdentifier expression);
}