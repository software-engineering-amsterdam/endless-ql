package org.uva.jomi.ui.elements.fields;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

public class MoneyField extends InputField<Double> implements PropertyChangeListener  {

	private JFormattedTextField textfield;
	private NumberFormat numberFormat;
	
	public MoneyField() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		this.textfield = new JFormattedTextField(numberFormat);
		this.textfield.addPropertyChangeListener("value", this);
	}
	
	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		panel.add(this.textfield);
		
		return panel;
	}

	@Override
	public Double getValue() {
		if(this.textfield.getValue() != null) {
			return ((Number)this.textfield.getValue()).doubleValue();
		}
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(this.listener != null && this.getValue() != null) {
			this.listener.valueDidChange(this);	
		}
	}
	
}
