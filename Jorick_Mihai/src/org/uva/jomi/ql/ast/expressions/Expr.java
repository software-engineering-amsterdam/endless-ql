package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.AstNode;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.statements.UnaryExpr;

abstract public class Expr extends AstNode {
	public interface Visitor<T> {
		T visit(IdentifierExpr expr);
		T visit(PrimaryExpr expr);
		T visit(BinaryExpr expr);
		T visit(GroupingExpr expr);
		T visit(UnaryExpr expr);
		T visit(AdditionExpr expr);
		T visit(SubtractionExpr expr);
		T visit(MultiplicationExpr expr);
		T visit(DivisionExpr expr);
		T visit(LessThanExpr expr);
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