package org.uva.jomi.ui.elements;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.uva.jomi.ql.ast.expressions.Expr;

public class ConditionalPanelElement implements BaseElement{

	private Expr expression;
	private BaseElement ifElement;
	private BaseElement elseElement;
	
	public ConditionalPanelElement(Expr expression, BaseElement ifElement, BaseElement elseElement) {
		this.expression = expression;
		this.ifElement = ifElement;
		this.elseElement = elseElement;
	}
	
	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		if(this.ifElement != null) {			
			panel.add(this.ifElement.build());
		}
		
		if(this.elseElement != null) {
			panel.add(this.elseElement.build());
		}
		
		return panel;
	}
		
}
