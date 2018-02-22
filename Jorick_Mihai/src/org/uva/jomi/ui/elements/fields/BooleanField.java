package org.uva.jomi.ui.elements.fields;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class BooleanField extends InputField<Boolean> {

	private JCheckBox checkbox;
	
	public BooleanField() {
		this.checkbox = new JCheckBox();
	}
	
	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		panel.add(this.checkbox);
		
		return panel;
	}

	@Override
	public Boolean getValue() {
		return this.checkbox.isSelected();
	}

}
