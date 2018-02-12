package org.uva.jomi.ql.ast;

public class QuestionStmt extends Stmt {
	final IndentifierExpr identifier;
	final String label;
	final QLType type;
	final Expr expression;

	public QuestionStmt(IndentifierExpr identifier, String label, QLType type) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
		this.expression = null;
	}
	
	public QuestionStmt(IndentifierExpr identifier, String label, QLType type, Expr expression) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
		this.expression = expression;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitQuestionStmt(this);
	}
}