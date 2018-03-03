package nl.khonraad.visitors;

import java.util.ArrayList;
import java.util.List;

import nl.khonraad.ExpressionLanguageBaseVisitor;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.domain.Question;
import nl.khonraad.domain.Questions;
import nl.khonraad.domain.Question.QuestionType;

public class TypeCheckingVisitor extends ExpressionLanguageBaseVisitor<Integer> {

	public Questions questions = new Questions();
	public List<String> forwardReferences = new ArrayList<String>();

	public static final String ERROR_ReferenceToUndefinedQuestion = "Reference to undefined question: ";
	public static final String ERROR_DuplicateQuestionDeclaration = "Duplicate question declaration: ";

	@Override
	public Integer visitForm(ExpressionLanguageParser.FormContext ctx) {
		
		visitChildren(ctx);
		
		if (forwardReferences.size() == 0) {
			return 0;
		}
		throw new RuntimeException(ERROR_ReferenceToUndefinedQuestion + forwardReferences.get(0));
	}

	@Override
	public Integer visitPartAnswerableQuestion(ExpressionLanguageParser.PartAnswerableQuestionContext ctx) {

		String identifier = ctx.identifier.getText();

		forwardReferences.remove(identifier);

		if (!questions.containsKey(identifier)) {

			String label = ctx.label.getText();
			String iotype = ctx.iotype.getText();

			questions.put(identifier, new Question(QuestionType.NOT_COMPUTED, identifier, label, iotype));

			return 0;
		}
		throw new RuntimeException(ERROR_DuplicateQuestionDeclaration + identifier);
	}

	@Override
	public Integer visitPartComputedQuestion(ExpressionLanguageParser.PartComputedQuestionContext ctx) {

		String identifier = ctx.identifier.getText();

		forwardReferences.remove(identifier);

		if (!questions.containsKey(identifier)) {

			String label = ctx.label.getText();
			String iotype = ctx.iotype.getText();

			Question question = new Question(QuestionType.COMPUTED, identifier, label, iotype);
			question.setValue(visit(ctx.expression()).toString());
			
			questions.put(identifier, question);
			
			return question.getValue();
		}
		throw new RuntimeException(ERROR_DuplicateQuestionDeclaration + identifier);
	}

	@Override
	public Integer visitLBL_Id_Expression(ExpressionLanguageParser.LBL_Id_ExpressionContext ctx) {

		String identifier = ctx.IDENTIFIER().getText();

		if (!questions.containsKey(identifier)) {
			forwardReferences.add(identifier);
			return 0;
		}
		return questions.get(identifier).getValue();
	}

}
