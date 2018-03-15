package org.uva.jomi.qls.ast.statements;

import org.uva.jomi.ql.ast.expressions.IdentifierExpr;
import org.uva.jomi.qls.ast.statements.widget.WidgetStmt;

public class QuestionStmt extends Stmt {
	
	private IdentifierExpr identifier;
	private WidgetStmt widget;
	
	public QuestionStmt(IdentifierExpr identifier) {
		this.identifier = identifier;
	}
	
	public QuestionStmt(IdentifierExpr identifier, WidgetStmt widget) {
		this.identifier = identifier;
		this.widget = widget;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
