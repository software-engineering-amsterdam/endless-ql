package ast.type;

import visiting.TypeVisitor;
import ast.AstNode;

public abstract class Type extends AstNode {

	public abstract <T, U> T accept(TypeVisitor<T, U> visitor, U ctx);
}