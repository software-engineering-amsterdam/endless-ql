package org.uva.jomi.qls.ast.statements.widget;

import org.uva.jomi.qls.ast.statements.Stmt;

public class RadioWidgetStmt extends WidgetStmt {
	
	private String positiveLabel = "Yes";
	private String negativeLabel = "No";
	
	public RadioWidgetStmt() {
		
	}
	
	public RadioWidgetStmt(String positiveLabel, String negativeLabel) {
		this.positiveLabel = positiveLabel;
		this.negativeLabel = negativeLabel;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
