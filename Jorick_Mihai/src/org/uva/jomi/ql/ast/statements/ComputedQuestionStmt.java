package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.IndentifierExpr;
import org.uva.jomi.ql.ast.statements.Stmt.Visitor;

public class ComputedQuestionStmt extends QuestionStmt {

	public final Expr expression;
	
	public ComputedQuestionStmt(IndentifierExpr identifier, String label, QLType type, Expr expression) {
		super(identifier, label, type);
		this.expression = expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitComputedQuestionStmt(this);
	}
}
