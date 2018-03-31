package org.uva.jomi.ui.views.fields;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.uva.jomi.ui.interpreter.value.BooleanValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.views.core.Panel;

public class BooleanField extends InputField implements ChangeListener {

	private JCheckBox checkbox;
	
	public BooleanField() {
		this.checkbox = new JCheckBox();	
		this.checkbox.addChangeListener(this);
		this.checkbox.setSelected(false);
	}
	
	@Override
	public Panel build() {
		Panel panel = new Panel();
		
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

	@Override
	public void clearValue() {
		this.checkbox.setSelected(false);
	}

}
