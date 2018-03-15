package org.uva.jomi.ui.elements.question;

import javax.swing.SpringLayout;

import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.elements.core.SpringUtilities;
import org.uva.jomi.ui.elements.fields.FieldFactory;
import org.uva.jomi.ui.elements.fields.InputField;
import org.uva.jomi.ui.elements.fields.InputField.InputFieldListener;
import org.uva.jomi.ui.interpreter.SymbolTable;

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
		panel.setLayout(new SpringLayout());

		Label label = new Label(this.question);
		panel.add(label.build());

		this.inputField = new FieldFactory().getField(this.type);
		this.inputField.setFieldListener(this);
		panel.add(this.inputField.build());

		SpringUtilities.makeGrid(panel,
								1, 2, // rows, cols
								5, 5, // initialX, initialY
								5, 5); // xPad, yPad

		return panel;
	}

	@Override
	public void valueDidChange(InputField field) {
		SymbolTable.getInstance().put(this.identifier, field.getValue());
	}

}
