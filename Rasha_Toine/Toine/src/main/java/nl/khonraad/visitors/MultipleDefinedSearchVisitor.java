package nl.khonraad.visitors;

import nl.khonraad.ExpressionLanguageBaseVisitor;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.domain.Question;
import nl.khonraad.domain.Questions;

public class MultipleDefinedSearchVisitor extends ExpressionLanguageBaseVisitor<Integer> {

	public Questions questions = new Questions();
	
	@Override
	public Integer visitLBL_Question(ExpressionLanguageParser.LBL_QuestionContext ctx) {

		if ( !questions.containsKey(ctx.variable.getText())) {
			Question question = new Question(ctx.variable.getText(), ctx.label.getText(), ctx.type.getText() );
			questions.put(ctx.variable.getText(), question);
			return 0;
		}
		throw new RuntimeException( "Multiple defined: " + ctx.variable.getText());
	}

	@Override
	public Integer visitLBL_ComputedQuestion(ExpressionLanguageParser.LBL_ComputedQuestionContext ctx)  {
		if ( !questions.containsKey(ctx.variable.getText())) {
			Question question = new Question(ctx.variable.getText(), ctx.label.getText(), ctx.type.getText() );
			question.setValue(visit(ctx.expression()).toString());
			questions.put(ctx.variable.getText(), question);
			return question.getValue();
		} 
		throw new RuntimeException( "Multiple defined: " + ctx.variable.getText());
	}

}
