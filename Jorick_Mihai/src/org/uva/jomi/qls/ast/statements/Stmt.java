package org.uva.jomi.qls.ast.statements;

import org.uva.jomi.qls.ast.AstNode;
import org.uva.jomi.qls.ast.statements.widget.CheckboxWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.DropdownWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.RadioWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.SpinboxWidgetStmt;
import org.uva.jomi.qls.ast.statements.widget.TextWidgetStmt;

public abstract class Stmt extends AstNode {
	public interface Visitor<T> {
		T visit(StylesheetStmt stmt);
		T visit(PageStmt stmt);
		T visit(SectionStmt stmt);
		T visit(QuestionStmt stmt);
		T visit(BlockStmt stmt);
		
		T visit(RadioWidgetStmt stmt);
		T visit(CheckboxWidgetStmt stmt);
		T visit(DropdownWidgetStmt stmt);
		T visit(SpinboxWidgetStmt stmt);
		T visit(TextWidgetStmt stmt);
	}
	
	public abstract <T> T accept(Visitor<T> visitor);
}