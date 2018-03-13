package org.uva.jomi.qls.ast.statements.widget;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.qls.ast.statements.Stmt;

public class DropdownWidgetStmt extends WidgetStmt {
	
	private List<String> options;
	
	public DropdownWidgetStmt(List<String> options) {
		this.options = options;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
