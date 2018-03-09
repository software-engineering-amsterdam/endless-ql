package org.uva.jomi.ui.elements.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.interpreter.BooleanValue;
import org.uva.jomi.ql.interpreter.EmptyValue;
import org.uva.jomi.ql.interpreter.GenericValue;
import org.uva.jomi.ui.ExpressionEvaluator;
import org.uva.jomi.ui.SymbolTable;
import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.ComputingInterface;
import org.uva.jomi.ui.elements.core.Panel;

public class ConditionalPanelElement implements BaseElement, ComputingInterface{

	private Expr expression;
	private BaseElement ifElement;
	private BaseElement elseElement;

	private JPanel ifPanel;
	private JPanel elsePanel;

	public ConditionalPanelElement(Expr expression, BaseElement ifElement, BaseElement elseElement) {
		this.expression = expression;
		this.ifElement = ifElement;
		this.elseElement = elseElement;

		SymbolTable.getInstance().watchers.add(this);
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();

		if(this.ifElement != null) {
			this.ifPanel = this.ifElement.build();
			panel.add(this.ifPanel);
		}

		if(this.elseElement != null) {
			this.elsePanel = this.elseElement.build();
			panel.add(this.elsePanel);
		}
		
		this.update();

		return panel;
	}

	@Override
	public void update() {
		ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

		GenericValue genericValue = expressionEvaluator.execute(this.expression);
		if(genericValue instanceof EmptyValue) {
			return;
		}
		BooleanValue value = (BooleanValue) genericValue;

		this.ifPanel.setVisible(value.getValue());
		this.elsePanel.setVisible(!value.getValue());
	}

}
