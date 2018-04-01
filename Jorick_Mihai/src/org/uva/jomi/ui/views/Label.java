package org.uva.jomi.ui.views;

import javax.swing.JLabel;

import org.uva.jomi.ui.views.core.Panel;

public class Label implements ViewGenerator {

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
