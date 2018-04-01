package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.AstNode;
import org.uva.jomi.ql.ast.QLType;

abstract public class Expression extends AstNode {
	public interface Visitor<T> {
		T visit(IdentifierExpression expression);
		T visit(GroupingExpression expression);
		T visit(AdditionExpression expression);
		T visit(SubtractionExpression expression);
		T visit(MultiplicationExpression expression);
		T visit(DivisionExpression expression);
		T visit(LessThanExpression expression);
		T visit(LessThanOrEqualExpression expression);
		T visit(GreaterThanExpression expression);
		T visit(GreaterThanOrEqualExpression expression);
		T visit(NotEqualExpression expression);
		T visit(EqualExpression expression);
		T visit(AndExpression expression);
		T visit(OrExpression expression);
		T visit(UnaryNotExpression expression);
		T visit(IntegerExpression expression);
		T visit(StringExpression expression);
		T visit(BooleanExpression expression);
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