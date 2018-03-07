package org.uva.jomi.ui.elements;

import javax.swing.JPanel;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.interpreter.EmptyValue;
import org.uva.jomi.ql.interpreter.GenericValue;
import org.uva.jomi.ui.ExpressionEvaluator;
import org.uva.jomi.ui.SymbolTable;

public class ComputedQuestionElement extends QuestionElement implements ComputingInterface {

	private Expr expression;

	public ComputedQuestionElement(String identifier, String question, String type, Expr expression) {
		super(identifier, question, type);
		this.expression = expression;

		SymbolTable.getInstance().watchers.add(this);
	}

	@Override
	public JPanel build() {
		JPanel panel = super.build();
		this.inputField.setEnabled(false);
		return panel;
	}

	@Override
	public void update() {

		ExpressionEvaluator interpreter = new ExpressionEvaluator();

		GenericValue genericValue = this.expression.visitExpr(interpreter);
		if(genericValue instanceof EmptyValue) {
			return;
		}

//		SymbolTable.getInstance().put(this.identifier, value);

		this.inputField.setValue(genericValue);
	}

}
