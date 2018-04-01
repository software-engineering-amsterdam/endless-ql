package org.uva.jomi.ui.views.fields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.uva.jomi.ui.interpreter.value.BooleanValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.views.core.Panel;

public class BooleanField extends InputField implements ActionListener {
	
	private JRadioButton yesButton;
	private JRadioButton noButton;
	private ButtonGroup group;
	
	
	public BooleanField() {		
		this.yesButton = new JRadioButton("Yes");
		this.yesButton.addActionListener(this);
		this.noButton = new JRadioButton("No");
		this.noButton.addActionListener(this);
	}
	
	@Override
	public Panel build() {
		Panel panel = new Panel();
		
		this.group = new ButtonGroup();
		this.group.add(this.yesButton);
		this.group.add(this.noButton);
		
		panel.add(this.yesButton);
		panel.add(this.noButton);
		
		return panel;
	}

	@Override
	public GenericValue getValue() {
		return new BooleanValue(this.yesButton.isSelected());
	}
	
	@Override
	public void setValue(GenericValue value) {
		BooleanValue booleanValue = (BooleanValue) value;
		this.yesButton.setSelected(booleanValue.getValue());
		this.noButton.setSelected(!booleanValue.getValue());
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.yesButton.setEnabled(enabled);
		this.noButton.setEnabled(enabled);
	}

	@Override
	public void clearValue() {
		this.group.clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.listener != null) {
			this.listener.valueDidChange(this);		
		}
	}

}
