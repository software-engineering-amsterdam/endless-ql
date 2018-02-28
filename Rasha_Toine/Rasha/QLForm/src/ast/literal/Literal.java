package ast.literal;

import checking.value.*;
import ast.AstNode;
import visiting.LiteralVisitor;

public abstract class Literal extends AstNode {

	public abstract Value getValue();
		
	public abstract <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx);
}
