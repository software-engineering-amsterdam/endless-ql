package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.AstNode;
import org.uva.jomi.ql.ast.QLType;

abstract public class Expr extends AstNode {
	public interface Visitor<T> {
		T visit(IdentifierExpr expr);
		T visit(GroupingExpr expr);
		T visit(AdditionExpr expr);
		T visit(SubtractionExpr expr);
		T visit(MultiplicationExpr expr);
		T visit(DivisionExpr expr);
		T visit(LessThanExpr expr);
		T visit(LessThanOrEqualExpr expr);
		T visit(GreaterThanExpr expr);
		T visit(GreaterThanOrEqualExpr expr);
		T visit(NotEqualExpr expr);
		T visit(EqualExpr expr);
		T visit(AndExpr expr);
		T visit(OrExpr expr);
		T visit(UnaryNotExpr expr);
		T visit(IntegerExpr expr);
		T visit(StringExpr expr);
		T visit(BooleanExpr expr);
	}

	public abstract <T> T accept(Visitor<T> visitor);

	private QLType type;
	private int lineNumber;
	private int columnNumber;
	
	public QLType getType() {
		return type;
	}

	public void setType(QLType type) {
		this.type = type;
	}
	
	public boolean hasType(QLType expectedType) {
		return this.getType() != expectedType;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColumnNumber() {
		return this.columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
	
	public <T> T visitExpr(Visitor<T> visitor) {
		return this.accept(visitor);
	}
	
	public int getExprId() {
		return this.getId();
	}
}