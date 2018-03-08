package org.uva.jomi.ui.elements.fields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.uva.jomi.ql.interpreter.BooleanValue;
import org.uva.jomi.ql.interpreter.GenericValue;

public class BooleanField extends InputField implements ChangeListener {

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
	public GenericValue getValue() {
		return new BooleanValue(this.checkbox.isSelected());
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		this.listener.valueDidChange(this);
	}
	
	@Override
	public void setValue(GenericValue value) {
		BooleanValue booleanValue = (BooleanValue) value;
		this.checkbox.setSelected(booleanValue.getValue());
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.checkbox.setEnabled(enabled);
	}

}
