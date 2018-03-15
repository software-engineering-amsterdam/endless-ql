package org.uva.jomi.ui.elements.fields;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.interpreter.value.IntegerValue;

public class IntegerField extends InputField implements PropertyChangeListener  {

	private JFormattedTextField textfield;
	private NumberFormat numberFormat;

	public IntegerField() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		this.textfield = new JFormattedTextField(numberFormat);
		this.textfield.addPropertyChangeListener("value", this);
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
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(this.listener != null && this.getValue() != null) {
			this.listener.valueDidChange(this);
		}
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

}
