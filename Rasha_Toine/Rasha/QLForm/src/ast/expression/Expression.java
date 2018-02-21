package ast.expression;

import visiting.ExpressionVisitor;
import ast.AstNode;

public abstract class Expression extends AstNode {
	  public abstract <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx);
	
}