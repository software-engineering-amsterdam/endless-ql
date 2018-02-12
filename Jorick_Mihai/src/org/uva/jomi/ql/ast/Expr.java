package org.uva.jomi.ql.ast;

abstract public class Expr extends AstNode {
	public interface Visitor<T> {
		T visitIndetifierExpr(IndentifierExpr expr);
		T visitPrimaryExpr(PrimaryExpr expr);
		T visitBinaryExpr(BinaryExpr expr);
		T visitGroupingExpr(GroupingExpr expr);
		T visitUnaryExpr(UnaryExpr expr);
	}
	
	abstract <T> T accept(Visitor<T> visitor);
	
	private QLType type;
	
	public QLType getType() {
		return type;
	}
	
	public void setType(QLType type) {
		this.type = type;
	}
}