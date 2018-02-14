package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.IndentifierExpr;
import org.uva.jomi.ql.ast.statements.Stmt.Visitor;

public class QuestionStmt extends Stmt {
	public final IndentifierExpr identifier;
	public final String label;
	public final QLType type;

	public QuestionStmt(IndentifierExpr identifier, String label, QLType type) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitQuestionStmt(this);
	}
}