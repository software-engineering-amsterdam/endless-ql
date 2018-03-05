package ql.ast.statement;

import ql.ast.Block;
import ql.ast.expression.Expression;
import ql.utils.CodeReference;
import ql.visiting.StatementVisitor;

public class IfThenStatement extends Statement {

	private final Expression expression; // condition
	private final Block ifBody;

	public IfThenStatement(Expression expression, Block ifBody, CodeReference location) {
		super(location);
		this.expression = expression;
		this.ifBody = ifBody;
	}

	public Expression getExpression() {
		return expression;
	}

	public Block getIfBody() {
		return ifBody;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		 return visitor.visit(this, ctx);
	}
}
