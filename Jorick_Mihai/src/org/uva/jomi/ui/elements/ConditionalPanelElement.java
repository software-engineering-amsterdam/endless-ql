package org.uva.jomi.ui.elements;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.interpreter.BooleanValue;
import org.uva.jomi.ql.interpreter.GenericValue;
import org.uva.jomi.ui.QLInterpreter;
import org.uva.jomi.ui.SymbolTable;

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
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		if(this.ifElement != null) {			
			this.ifPanel = this.ifElement.build();
			panel.add(this.ifPanel);
		}
		
		if(this.elseElement != null) {
			this.elsePanel = this.elseElement.build();
			panel.add(this.elsePanel);
		}
		
		return panel;
	}
	
	@Override
	public void update() {
		QLInterpreter interpreter = new QLInterpreter();
				
		BooleanValue value = (BooleanValue) this.expression.visitExpr(interpreter);;
		if(value == null) {
			return;
		}
		
		this.ifPanel.setVisible(value.getValue());
		this.elsePanel.setVisible(!value.getValue());
	}
		
}
