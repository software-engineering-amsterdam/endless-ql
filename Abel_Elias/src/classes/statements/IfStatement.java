package classes.statements;

import classes.Block;
import classes.CodeBlock;
import classes.expressions.BooleanExpression;
import classes.expressions.Expression;

import javax.naming.ldap.ExtendedRequest;

public class IfStatement extends Statement {

    private BooleanExpression expression;
    private Block block;
    private CodeBlock code;

    public IfStatement(BooleanExpression expression, CodeBlock code, Block block) {
        super(code);
        this.expression = expression;
        this.code = code;
        this.block = block;
    }

    public BooleanExpression getExpression() {
        return expression;
    }

    public CodeBlock getCode() {
        return code;
    }
    public Block getBlock() {return block;}

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visitIfStatementWithExpr(this, expression);
    }

    @Override
    public void accept(StatementVisitor visitor, BooleanExpression expression) {
        visitor.visitIfStatementWithExpr(this, expression);
    }
}
