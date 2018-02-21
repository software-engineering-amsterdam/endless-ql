package org.uva.jomi.ui.elements;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;

public class ComputedQuestionElement extends QuestionElement {

	private Expr expression;
	
	public ComputedQuestionElement(ComputedQuestionStmt question) {
		super(question);
		this.expression = question.expression;
	}
	
}
