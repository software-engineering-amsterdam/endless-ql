package ast.statement;

import ast.Block;
import ast.expression.Expression;
import utils.CodeReference;

public class IfThenStatement extends Statement {

	private final Expression expression;
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
}
