package classes.statements;

import classes.expressions.Expression;

public interface StatementVisitor {
    public void visitQuestion(Question question);
    public void visitIfStatement(IfStatement ifStatement);
    public void visitQuestionWithExpr(Question question, Expression expression);
    public void visitIfStatementWithExpr(IfStatement ifStatement, Expression expression);
}
