package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

public class ComputedQuestionStmt extends QuestionStmt {

	private final Expr expression;
	
	public ComputedQuestionStmt(IdentifierExpr identifier, String label, QLType type, Expr expression) {
		super(identifier, label, type);
		this.expression = expression;
	}
	
	public Expr getExp() {
		return expression;
	}
	
	public int getExpId() {
		return expression.getId();
	}
	
	public QLType getExprType() {
		return expression.getType();
	}
	
	public int getExprLineNumber() {
		return this.expression.getLineNumber();
	}

	public int getExprColumnNumber() {
		return this.expression.getColumnNumber();
	}
	
	public <T> T visitExpr(Expr.Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
