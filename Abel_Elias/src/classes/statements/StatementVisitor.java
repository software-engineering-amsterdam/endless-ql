package classes.statements;

import classes.expressions.BooleanExpression;
import classes.expressions.Expression;

public interface StatementVisitor {
    public void visitQuestion(Question question);
    public void visitQuestionWithExpr(Question question, BooleanExpression expression);
    public void visitIfStatementWithExpr(IfStatement ifStatement, BooleanExpression expression);
}
