package org.uva.jomi.ui.views.fields;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.interpreter.value.StringValue;
import org.uva.jomi.ui.views.core.Panel;

public class TextField extends InputField implements DocumentListener {

	private JTextField textfield;
	
	public TextField() {
		this.textfield = new JTextField();
		this.textfield.getDocument().addDocumentListener(this);
	}
	
	@Override
	public Panel build() {
		Panel panel = new Panel();
		
		panel.add(this.textfield);
		
		return panel;
	}

	@Override
	public GenericValue getValue() {
		return new StringValue(this.textfield.getText());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if(this.listener != null) {
			this.listener.valueDidChange(this);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if(this.listener != null) {
			this.listener.valueDidChange(this);
		}		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		if(this.listener != null) {
			this.listener.valueDidChange(this);
		}		
	}

	@Override
	public void setValue(GenericValue value) {
		StringValue stringValue = (StringValue) value;
		this.textfield.setText(stringValue.getValue());
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.textfield.setEnabled(enabled);
	}

	@Override
	public void clearValue() {
		this.textfield.setText(null);		
	}
}
