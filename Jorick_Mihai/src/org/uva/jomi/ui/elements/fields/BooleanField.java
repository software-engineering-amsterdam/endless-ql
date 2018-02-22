package org.uva.jomi.ui.elements.fields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BooleanField extends InputField<Boolean> implements ChangeListener {

	private JCheckBox checkbox;
	
	public BooleanField() {
		this.checkbox = new JCheckBox();
		
		this.checkbox.addChangeListener(this);
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


	@Override
	public void stateChanged(ChangeEvent e) {
		this.listener.valueDidChange(this);
	}

}
