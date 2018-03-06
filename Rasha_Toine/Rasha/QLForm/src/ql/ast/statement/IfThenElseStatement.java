package ql.ast.statement;

import ql.ast.Block;
import ql.ast.expression.Expression;
import ql.utils.CodeReference;

public class IfThenElseStatement extends IfThenStatement {

	private final Block elseBody;

	public IfThenElseStatement(Expression expression, Block ifBody, Block elseBody, CodeReference location) {
		super(expression, ifBody, location);
		this.elseBody = elseBody;
	}

	public Block getElseBody() {
		return elseBody;
	}
}