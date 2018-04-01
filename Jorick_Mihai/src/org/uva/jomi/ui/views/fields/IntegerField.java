package org.uva.jomi.ui.views.fields;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import org.uva.jomi.ui.interpreter.value.EmptyValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.interpreter.value.IntegerValue;
import org.uva.jomi.ui.views.core.Panel;

public class IntegerField extends InputField implements PropertyChangeListener  {

	private JFormattedTextField textfield;

	public IntegerField() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		this.textfield = new JFormattedTextField(numberFormat);
		this.textfield.addPropertyChangeListener("value", this);
	}
	
	private void valueDidChange() {
		if(this.listener != null) {
			this.listener.valueDidChange(this);
		}
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();

		panel.add(this.textfield);

		return panel;
	}

	@Override
	public GenericValue getValue() {
		if(this.textfield.getValue() != null) {
			return new IntegerValue(((Number)this.textfield.getValue()).intValue());
		}
		return new EmptyValue();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.valueDidChange();
	}

	@Override
	public void setValue(GenericValue value) {
		IntegerValue integerValue = (IntegerValue) value;
		this.textfield.setValue(integerValue.getValue());
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.textfield.setEnabled(enabled);
	}

	@Override
	public void clearValue() {
		this.textfield.removePropertyChangeListener("value", this);
		this.textfield.setValue(null);		
		this.textfield.addPropertyChangeListener("value", this);
	}

}
