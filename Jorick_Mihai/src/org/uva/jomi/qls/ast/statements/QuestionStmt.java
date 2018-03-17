package org.uva.jomi.qls.ast.statements;

import org.uva.jomi.ql.ast.expressions.IdentifierExpression;
import org.uva.jomi.qls.ast.statements.widget.WidgetStmt;

public class QuestionStmt extends Stmt {
	
	private IdentifierExpression identifier;
	private WidgetStmt widget;
	
	public QuestionStmt(IdentifierExpression identifier) {
		this.identifier = identifier;
	}
	
	public QuestionStmt(IdentifierExpression identifier, WidgetStmt widget) {
		this.identifier = identifier;
		this.widget = widget;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
