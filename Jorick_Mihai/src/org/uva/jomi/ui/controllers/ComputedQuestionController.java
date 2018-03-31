package org.uva.jomi.ui.controllers;

import java.util.List;

import org.uva.jomi.ui.interpreter.ExpressionEvaluator;
import org.uva.jomi.ui.interpreter.IdentifierFinder;
import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.interpreter.value.EmptyValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.models.Question;
import org.uva.jomi.ui.views.core.Panel;

public class ComputedQuestionController extends QuestionController {
	
	public ComputedQuestionController(Question question) {
		super(question);
	}

	@Override
	public Panel build() {
		Panel panel = super.build();
		this.inputField.setEnabled(false);
		this.computeAnswer();
		return panel;
	}

	@Override
	public void update(String key, GenericValue value) {
		super.update(key, value);
		
		// Check if an identifier where the expression depends on is updated.
		List<String> internalIdentifiers = new IdentifierFinder().find(this.question.getExpression());
		if(internalIdentifiers.contains(key)) {
			this.computeAnswer();			
		}
	}
	
	private void computeAnswer() {
		GenericValue genericValue = new ExpressionEvaluator().execute(this.question.getExpression());
		if(genericValue instanceof EmptyValue) {
			return;
		}

		// Store computed variable as result of the question
		SymbolTable.getInstance().put(this.question.getIdentifier(), genericValue);

		this.inputField.setValue(genericValue);
	}

}
