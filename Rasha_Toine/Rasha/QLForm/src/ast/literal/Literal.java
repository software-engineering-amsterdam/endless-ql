package ast.literal;

import ast.AstNode;
import visiting.LiteralVisitor;
import visiting.value.Value;

public abstract class Literal extends AstNode {

	public abstract Value getValue();
		
	public abstract <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx);
}
