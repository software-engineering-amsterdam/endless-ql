package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.AstNode;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.statements.UnaryExpr;

abstract public class Expr extends AstNode {
	public interface Visitor<T> {
		T visitIndetifierExpr(IdentifierExpr expr);
		T visitPrimaryExpr(PrimaryExpr expr);
		T visitBinaryExpr(BinaryExpr expr);
		T visitGroupingExpr(GroupingExpr expr);
		T visitUnaryExpr(UnaryExpr expr);
	}
	
	public abstract <T> T accept(Visitor<T> visitor);
	
	private QLType type;
	
	public QLType getType() {
		return type;
	}
	
	public void setType(QLType type) {
		this.type = type;
	}
}