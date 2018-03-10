package org.uva.jomi.ui.elements.question;

import javax.swing.JLabel;

import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.core.Panel;

public class Label implements BaseElement {

	private String text;
	
	public Label(String text) {
		this.text = text;
	}
	
	
	@Override
	public Panel build() {
		Panel panel = new Panel();
		
		JLabel label = new JLabel(this.text);
		panel.add(label);
		
		return panel;
	}

}
