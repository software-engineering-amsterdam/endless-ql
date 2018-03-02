package nl.khonraad.visitors;

import java.util.ArrayList;
import java.util.List;

import nl.khonraad.ExpressionLanguageBaseVisitor;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.domain.Question;
import nl.khonraad.domain.Questions;

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
	public Integer visitLBL_Question(ExpressionLanguageParser.LBL_QuestionContext ctx) {

		forwardReferences.remove(ctx.variable.getText());

		if (!questions.containsKey(ctx.variable.getText())) {
			Question question = new Question(false, ctx.variable.getText(), ctx.label.getText(), ctx.type.getText());
			questions.put(ctx.variable.getText(), question);
			return 0;
		}
		throw new RuntimeException(ERROR_DuplicateQuestionDeclaration + ctx.variable.getText());
	}

	@Override
	public Integer visitLBL_ComputedQuestion(ExpressionLanguageParser.LBL_ComputedQuestionContext ctx) {

		forwardReferences.remove(ctx.variable.getText());

		if (!questions.containsKey(ctx.variable.getText())) {
			Question question = new Question(true, ctx.variable.getText(), ctx.label.getText(), ctx.type.getText());
			question.setValue(visit(ctx.expression()).toString());
			questions.put(ctx.variable.getText(), question);
			return question.getValue();
		}
		throw new RuntimeException(ERROR_DuplicateQuestionDeclaration + ctx.variable.getText());
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
