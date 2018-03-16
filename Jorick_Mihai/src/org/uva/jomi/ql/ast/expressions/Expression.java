package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.AstNode;
import org.uva.jomi.ql.ast.QLType;

abstract public class Expression extends AstNode {
	public interface Visitor<T> {
		T visit(IdentifierExpression expr);
		T visit(GroupingExpression expr);
		T visit(AdditionExpression expr);
		T visit(SubtractionExpression expr);
		T visit(MultiplicationExpression expr);
		T visit(DivisionExpression expr);
		T visit(LessThanExpression expr);
		T visit(LessThanOrEqualExpression expr);
		T visit(GreaterThanExpression expr);
		T visit(GreaterThanOrEqualExpression expr);
		T visit(NotEqualExpression expr);
		T visit(EqualExpression expr);
		T visit(AndExpression expr);
		T visit(OrExpression expr);
		T visit(UnaryNotExpression expr);
		T visit(IntegerExpression expr);
		T visit(StringExpression expr);
		T visit(BooleanExpression expr);
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
	
	public <T> T visitExpression(Visitor<T> visitor) {
		return this.accept(visitor);
	}
	
	public int getExpressionId() {
		return this.getNodeId();
	}
}