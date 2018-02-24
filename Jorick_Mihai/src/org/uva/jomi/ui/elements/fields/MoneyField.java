package org.uva.jomi.ui.elements.fields;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MoneyField extends InputField<Integer> {

	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
//		panel.add(this.checkbox);
		
		return panel;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
