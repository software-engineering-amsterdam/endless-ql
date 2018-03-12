package org.uva.jomi.qls.ast.statements.widget;

import org.uva.jomi.qls.ast.statements.Stmt;

public class SpinboxWidgetStmt extends WidgetStmt {

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
