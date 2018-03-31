package org.uva.jomi.ui.controllers;

import javax.swing.SpringLayout;

import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.interpreter.SymbolTableListener;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.models.Question;
import org.uva.jomi.ui.views.Label;
import org.uva.jomi.ui.views.core.Panel;
import org.uva.jomi.ui.views.core.SpringUtilities;
import org.uva.jomi.ui.views.fields.FieldFactory;
import org.uva.jomi.ui.views.fields.InputField;
import org.uva.jomi.ui.views.fields.InputField.InputFieldListener;

public class QuestionController extends Controller implements InputFieldListener, SymbolTableListener {

	protected Question question;
	protected Panel view;
	
	protected InputField inputField;

	public QuestionController(Question question) {
		this.question = question;
		SymbolTable.getInstance().addWatcher(this);
	}

	@Override
	public Panel build() {
		view = new Panel();
		view.setLayout(new SpringLayout());

		Label label = new Label(this.question.getQuestion());
		view.add(label.build());

		this.inputField = new FieldFactory().getField(this.question.getType());
		this.inputField.setFieldListener(this);
		view.add(this.inputField.build());

		SpringUtilities.makeGrid(view,
								1, 2, // rows, cols
								5, 5, // initialX, initialY
								5, 5); // xPad, yPad

		// Set views default invisible
		view.setVisible(false);
		
		return view;
	}

	@Override
	public void valueDidChange(InputField field) {
		SymbolTable.getInstance().put(this.question.getIdentifier(), field.getValue());
	}

	@Override
	public void update(String key, GenericValue value) {
		if(this.view != null) {
			// Check if question needs to be showed	
			this.view.setVisible(this.question.showQuestion());
			
			// Remove 
			if(!this.view.isVisible()) {
				SymbolTable.getInstance().remove(this.question.getIdentifier());
				this.inputField.clearValue();
			}
		}
	}

}
