package ast.model.expressions.binary;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public abstract class BinaryExpression extends Expression {

    private Expression leftSide;
    private Expression rightSide;

    public BinaryExpression(Expression leftSide, Expression rightSide, Integer startLine, Integer endLine) {
        super(startLine, endLine);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Expression getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(Expression leftSide) {
        this.leftSide = leftSide;
    }

    public Expression getRightSide() {
        return rightSide;
    }

    public void setRightSide(Expression rightSide) {
        this.rightSide = rightSide;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        this.leftSide.accept(visitor);
        this.rightSide.accept(visitor);
    }

}
