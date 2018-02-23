package org.uva.jomi.ui.elements;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.uva.jomi.ui.SymbolTable;
import org.uva.jomi.ui.elements.fields.FieldFactory;
import org.uva.jomi.ui.elements.fields.InputField;
import org.uva.jomi.ui.elements.fields.InputField.InputFieldListener;

public class QuestionElement implements BaseElement, InputFieldListener {

	private String identifier;
	private String question;
	private String type;
	
	public QuestionElement(String identifier, String question, String type) {
		this.identifier = identifier;
		this.question = question;
		this.type = type;
	}

	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		
		panel.add(new JLabel(this.question));
		
		InputField inputField = new FieldFactory().getField(this.type);
		if(inputField != null) {
			inputField.setFieldListener(this);
			panel.add(inputField.build());
		}		
		
		return panel;
	}

	@Override
	public void valueDidChange(InputField<?> field) {
		SymbolTable.getInstance().put(this.identifier, field.getValue());
	}
	
}
