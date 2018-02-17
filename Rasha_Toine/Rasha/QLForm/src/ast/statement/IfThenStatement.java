package ast.statement;

import ast.Block;
import ast.expression.Expression;
import utils.CodeReference;
import visiting.StatementVisitor;

public class IfThenStatement extends Statement {

	private final Expression expression; // condition
	private final Block IfBody;

	public IfThenStatement(Expression expression, Block IfBody, CodeReference location) {
		super(location);
		this.expression = expression;
		this.IfBody = IfBody;
	}

	public Expression getExpression() {
		return expression;
	}

	public Block getIfBody() {
		return IfBody;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		 return visitor.visit(this, ctx);
	}
}
