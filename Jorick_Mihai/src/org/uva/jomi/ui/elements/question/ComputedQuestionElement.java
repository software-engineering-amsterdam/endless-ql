package org.uva.jomi.ui.elements.question;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.interpreter.EmptyValue;
import org.uva.jomi.ql.interpreter.GenericValue;
import org.uva.jomi.ui.ExpressionEvaluator;
import org.uva.jomi.ui.SymbolTable;
import org.uva.jomi.ui.elements.ComputingInterface;
import org.uva.jomi.ui.elements.core.Panel;

public class ComputedQuestionElement extends QuestionElement implements ComputingInterface {

	private Expr expression;

	public ComputedQuestionElement(String identifier, String question, String type, Expr expression) {
		super(identifier, question, type);
		this.expression = expression;

		SymbolTable.getInstance().watchers.add(this);
	}

	@Override
	public Panel build() {
		Panel panel = super.build();
		this.inputField.setEnabled(false);
		return panel;
	}

	@Override
	public void update() {

		ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

		GenericValue genericValue = expressionEvaluator.execute(this.expression);
		if(genericValue instanceof EmptyValue) {
			return;
		}

//		SymbolTable.getInstance().put(this.identifier, value);

		this.inputField.setValue(genericValue);
	}

}
