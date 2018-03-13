package classes.statements;

import classes.Block;
import classes.CodeBlock;
import classes.expressions.Expression;

import javax.naming.ldap.ExtendedRequest;

public class IfStatement extends Statement {

    private Expression expression;
    private Block block;
    private CodeBlock code;

    public IfStatement(Expression expression, CodeBlock code, Block block) {
        super(code);
        this.expression = expression;
        this.code = code;
        this.block = block;
    }

    public Expression getExpression() {
        return expression;
    }

    public CodeBlock getCode() {
        return code;
    }
    public Block getBlock() {return block;}

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visitIfStatement(this);
    }

    @Override
    public void accept(StatementVisitor visitor, Expression expression) {
        visitor.visitIfStatementWithExpr(this, expression);
    }
}
