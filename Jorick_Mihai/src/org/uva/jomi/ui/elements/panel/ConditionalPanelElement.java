package org.uva.jomi.ui.elements.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.interpreter.SymbolTableListener;
import org.uva.jomi.ui.interpreter.ExpressionEvaluator;
import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.interpreter.value.BooleanValue;
import org.uva.jomi.ui.interpreter.value.EmptyValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;

public class ConditionalPanelElement implements BaseElement, SymbolTableListener{

	private Expr expression;
	private BaseElement ifElement;
	private BaseElement elseElement;

	private JPanel ifPanel;
	private JPanel elsePanel;

	public ConditionalPanelElement(Expr expression, BaseElement ifElement, BaseElement elseElement) {
		this.expression = expression;
		this.ifElement = ifElement;
		this.elseElement = elseElement;

		SymbolTable.getInstance().addWatcher(this);
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();

		if(this.ifElement != null) {
			this.ifPanel = this.ifElement.build();
			this.ifPanel.setVisible(false);
			panel.add(this.ifPanel);
		}

		if(this.elseElement != null) {
			this.elsePanel = this.elseElement.build();
			this.elsePanel.setVisible(false);
			panel.add(this.elsePanel);
		}
		
		this.update();

		return panel;
	}
	
	public void update() {
		ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

		GenericValue genericValue = expressionEvaluator.execute(this.expression);
		if(genericValue instanceof EmptyValue) {
			return;
		}
		BooleanValue booleanValue = (BooleanValue) genericValue;
		this.ifPanel.setVisible(booleanValue.getValue());
		
		if(this.elseElement != null) {
			this.elsePanel.setVisible(!booleanValue.getValue());	
		}
	}
	
	@Override
	public void update(String key, GenericValue value) {
		this.update();
	}
}
