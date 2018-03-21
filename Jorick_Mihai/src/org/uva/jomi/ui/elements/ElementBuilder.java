package org.uva.jomi.ui.elements;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.statements.BlockStatement;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStatement;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.IfElseStatement;
import org.uva.jomi.ql.ast.statements.IfStatement;
import org.uva.jomi.ql.ast.statements.QuestionStatement;
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.elements.panel.ConditionalPanelElement;
import org.uva.jomi.ui.elements.panel.FormPanel;
import org.uva.jomi.ui.elements.panel.PanelElement;
import org.uva.jomi.ui.elements.question.ComputedQuestionElement;
import org.uva.jomi.ui.elements.question.QuestionElement;

public class ElementBuilder implements Statement.Visitor<BaseElement> {

	private List<BaseElement> generate(List<Statement> statements) {
		List<BaseElement> elements = new ArrayList<BaseElement>();

		for (Statement statement : statements) {
			elements.add(statement.accept(this));
		}

		return elements;
	}

	public List<Panel> build(List<Statement> statements) {
		List<Panel> elements = new ArrayList<Panel>();

		for (BaseElement baseElement : this.generate(statements)) {
			elements.add(baseElement.build());
		}

		return elements;
	}

	@Override
	public BaseElement visit(FormStatement form) {
		FormPanel panel = new FormPanel();

		panel.addElement(form.visitBlockStmt(this));

		return panel;
	}

	@Override
	public BaseElement visit(BlockStatement block) {
		PanelElement panel = new PanelElement();

		for (Statement statement : block.getStatements()) {
			panel.addElement(statement.accept(this));
		}

		return panel;
	}

	@Override
	public BaseElement visit(QuestionStatement questionStmt) {
		return new QuestionElement(questionStmt.getName(), questionStmt.getLabel(), questionStmt.getType().toString());
	}

	@Override
	public BaseElement visit(ComputedQuestionStatement questionStmt) {
		return new ComputedQuestionElement(questionStmt.getName(), questionStmt.getLabel(), questionStmt.getType().toString(), questionStmt.getExpression());
	}

	@Override
	public BaseElement visit(IfStatement stmt) {
		BaseElement ifElement = stmt.visitIfBlockStatement(this);
		return new ConditionalPanelElement(stmt.getExpression(), ifElement, null);
	}

	@Override
	public BaseElement visit(IfElseStatement stmt) {
		BaseElement ifElement = stmt.visitIfBlockStatement(this);
		BaseElement elseElement = stmt.visitElseBlockStatement(this);
		return new ConditionalPanelElement(stmt.getExpression(), ifElement, elseElement);
	}

}
