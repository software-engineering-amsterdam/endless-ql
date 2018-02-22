package org.uva.jomi.ui.generator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.ComputedQuestionElement;
import org.uva.jomi.ui.elements.ConditionalPanelElement;
import org.uva.jomi.ui.elements.PanelElement;
import org.uva.jomi.ui.elements.QuestionElement;

public class UIBuilder implements Stmt.Visitor<BaseElement> {

	private List<BaseElement> generate(List<Stmt> statements) {
		List<BaseElement> elements = new ArrayList<BaseElement>();
		
		for (Stmt statement : statements) {
			elements.add(statement.accept(this));
		}
		
		return elements;
	}
	
	public List<JPanel> build(List<Stmt> statements) {
		List<JPanel> elements = new ArrayList<JPanel>();
		
		for (BaseElement baseElement : this.generate(statements)) {
			elements.add(baseElement.build());
		}
		
		return elements;
	}
	
	@Override
	public BaseElement visit(FormStmt form) {
		PanelElement panel = new PanelElement();
		
		panel.addElement(form.blockStmt.accept(this));
		
		return panel;
	}

	@Override
	public BaseElement visit(BlockStmt block) {
		PanelElement panel = new PanelElement();
		
		for (Stmt statement : block.statements) {
			panel.addElement(statement.accept(this));
		}
		
		return panel;
	}

	@Override
	public BaseElement visit(QuestionStmt questionStmt) {
		return new QuestionElement(questionStmt);
	}

	@Override
	public BaseElement visit(ComputedQuestionStmt questionStmt) {
		return new ComputedQuestionElement(questionStmt);
	}

	@Override
	public BaseElement visit(IfStmt stmt) {
		BaseElement ifElement = stmt.blockStmt.accept(this);
		return new ConditionalPanelElement(stmt.expression, ifElement, null);
	}

	@Override
	public BaseElement visit(IfElseStmt stmt) {
		BaseElement ifElement = stmt.ifBlockStmt.accept(this);
		BaseElement elseElement = stmt.elseBlockStmt.accept(this);
		return new ConditionalPanelElement(stmt.expression, ifElement, elseElement);
	}
	
}
