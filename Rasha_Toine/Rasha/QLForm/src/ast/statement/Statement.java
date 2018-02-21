package ast.statement;

import ast.AstNode;
import utils.CodeReference;
import visiting.StatementVisitor;

public abstract class Statement extends AstNode {

	public Statement(CodeReference location) {
		super(location);
	}
	
	public abstract <T, U> T accept(StatementVisitor<T, U> visitor, U ctx);
}