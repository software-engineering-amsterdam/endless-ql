package ql.gui.alternative;

import ql.ast.expression.Identifier;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.statement.AnswerableQuestion;
import ql.gui.alternative.widget.Widget;
import ql.helpers.Observable;
import ql.helpers.Observer;

public class AnswerableQuestionController implements Observer {

	protected AnswerableQuestion question;
	protected Widget<?> widget;

	public AnswerableQuestionController(AnswerableQuestion question, Widget<?> widget) {
		
		this.question	= question;
		this.widget		= widget;
		
		question.getIdentifier().setValue(new UndefinedLiteral());
		widget.addObserver(this);
	}

	@Override
	public void update(Observable observable, Literal<?>[] values) {
		
		String widgetValue			= widget.getValue();
		Identifier identifier		= question.getIdentifier();
		Literal<?> identifierValue	= widgetValue.trim().isEmpty()? new UndefinedLiteral() : Literal.create(identifier.getType(), widgetValue);
		
		identifier.setValue(identifierValue);
	}
}
