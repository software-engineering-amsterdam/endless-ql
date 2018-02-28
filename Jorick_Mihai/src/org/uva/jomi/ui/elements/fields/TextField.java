package org.uva.jomi.ui.elements.fields;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextField extends InputField<String> implements DocumentListener {

	private JTextField textfield;
	
	public TextField() {
		this.textfield = new JTextField();
		this.textfield.getDocument().addDocumentListener(this);
	}
	
	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		panel.add(this.textfield);
		
		return panel;
	}

	@Override
	public String getValue() {
		return this.textfield.getText();
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

}
