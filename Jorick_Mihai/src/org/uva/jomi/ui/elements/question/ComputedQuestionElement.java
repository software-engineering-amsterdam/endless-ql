package org.uva.jomi.ui.elements.question;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.interpreter.SymbolTableListener;
import org.uva.jomi.ui.interpreter.ExpressionEvaluator;
import org.uva.jomi.ui.interpreter.IdentifierFinder;
import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.interpreter.value.EmptyValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;

public class ComputedQuestionElement extends QuestionElement implements SymbolTableListener {

	private Expr expression;

	public ComputedQuestionElement(String identifier, String question, String type, Expr expression) {
		super(identifier, question, type);
		this.expression = expression;

		SymbolTable.getInstance().addWatcher(this);
	}

	@Override
	public Panel build() {
		Panel panel = super.build();
		this.inputField.setEnabled(false);
		return panel;
	}

	@Override
	public void update(String key, GenericValue value) {
		// Check if an identifier where the expression depends on is updated.
		List<String> internalIdentifiers = new IdentifierFinder().find(this.expression);
		if(internalIdentifiers.contains(key)) {
			
			GenericValue genericValue = new ExpressionEvaluator().execute(this.expression);
			if(genericValue instanceof EmptyValue) {
				return;
			}

			// Store computed variable as result of the question
			SymbolTable.getInstance().put(this.identifier, value);

			this.inputField.setValue(genericValue);	
			
			System.out.println("Updated computed question");
		}
		
	}

}
