package org.uva.jomi.ui.elements;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.elements.panel.ConditionalPanelElement;
import org.uva.jomi.ui.elements.panel.FormPanel;
import org.uva.jomi.ui.elements.panel.PanelElement;
import org.uva.jomi.ui.elements.question.ComputedQuestionElement;
import org.uva.jomi.ui.elements.question.QuestionElement;

public class ElementBuilder implements Stmt.Visitor<BaseElement> {

	private List<BaseElement> generate(List<Stmt> statements) {
		List<BaseElement> elements = new ArrayList<BaseElement>();

		for (Stmt statement : statements) {
			elements.add(statement.accept(this));
		}

		return elements;
	}

	public List<Panel> build(List<Stmt> statements) {
		List<Panel> elements = new ArrayList<Panel>();

		for (BaseElement baseElement : this.generate(statements)) {
			elements.add(baseElement.build());
		}

		return elements;
	}

	@Override
	public BaseElement visit(FormStmt form) {
		FormPanel panel = new FormPanel();

		panel.addElement(form.visitBlockStmt(this));

		return panel;
	}

	@Override
	public BaseElement visit(BlockStmt block) {
		PanelElement panel = new PanelElement();

		for (Stmt statement : block.getStatements()) {
			panel.addElement(statement.accept(this));
		}

		return panel;
	}

	@Override
	public BaseElement visit(QuestionStmt questionStmt) {
		return new QuestionElement(questionStmt.getIdentifierName(), questionStmt.getLabel(), questionStmt.getType().toString());
	}

	@Override
	public BaseElement visit(ComputedQuestionStmt questionStmt) {
		return new ComputedQuestionElement(questionStmt.getIdentifierName(), questionStmt.getLabel(), questionStmt.getType().toString(), questionStmt.getExp());
	}

	@Override
	public BaseElement visit(IfStmt stmt) {
		BaseElement ifElement = stmt.visitIfBlockStmt(this);
		return new ConditionalPanelElement(stmt.getExpr(), ifElement, null);
	}

	@Override
	public BaseElement visit(IfElseStmt stmt) {
		BaseElement ifElement = stmt.visitIfBlockStmt(this);
		BaseElement elseElement = stmt.visitElseBlockStmt(this);
		return new ConditionalPanelElement(stmt.getExpr(), ifElement, elseElement);
	}

}
