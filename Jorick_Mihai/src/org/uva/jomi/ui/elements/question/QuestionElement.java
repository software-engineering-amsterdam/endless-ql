package org.uva.jomi.ui.elements.question;

import org.uva.jomi.ui.SymbolTable;
import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.elements.fields.FieldFactory;
import org.uva.jomi.ui.elements.fields.InputField;
import org.uva.jomi.ui.elements.fields.InputField.InputFieldListener;

public class QuestionElement implements BaseElement, InputFieldListener {

	protected String identifier;
	protected String question;
	protected String type;
	protected InputField inputField;
	
	public QuestionElement(String identifier, String question, String type) {
		this.identifier = identifier;
		this.question = question;
		this.type = type;
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();
		
		Label label = new Label(this.question);
		panel.add(label.build());
		
		this.inputField = new FieldFactory().getField(this.type);
		if(this.inputField != null) {
			inputField.setFieldListener(this);
			panel.add(inputField.build());
		}		
		
		return panel;
	}

	@Override
	public void valueDidChange(InputField field) {
		SymbolTable.getInstance().put(this.identifier, field.getValue());
	}
	
}
