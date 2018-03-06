package nl.khonraad.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.khonraad.ExpressionLanguageBaseVisitor;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.domain.AnswerableQuestion;
import nl.khonraad.domain.ComputedQuestion;
import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;

public class TypeCheckingVisitor extends ExpressionLanguageBaseVisitor<Value> {

	public Map<String,AnswerableQuestion> answerableQuestions = new HashMap<String,AnswerableQuestion>();
	public Map<String,ComputedQuestion> computedQuestions = new HashMap<String,ComputedQuestion>();
	
	public List<String> forwardReferences = new ArrayList<String>();
	public List<String> foundReferences = new ArrayList<String>();

	public static final String ERROR_ReferenceToUndefinedQuestion = "Reference to undefined question: ";
	public static final String ERROR_DuplicateQuestionDeclaration = "Duplicate question declaration: ";

	@Override
	public Value visitForm(ExpressionLanguageParser.FormContext ctx) {
		
		visitChildren(ctx);
		
		for ( String s : foundReferences ) {
			forwardReferences.remove(s);
		}
		if (forwardReferences.size() == 0) {
			return null;
		}
		throw new RuntimeException(ERROR_ReferenceToUndefinedQuestion + forwardReferences.get(0));
	}

	@Override
	public Value visitPartAnswerableQuestion(ExpressionLanguageParser.PartAnswerableQuestionContext ctx) {

		String identifier = ctx.Identifier().getText();


		if (!answerableQuestions.containsKey(identifier)) {

			foundReferences.add(identifier);

			String label = ctx.QuotedString().getText();
			Type type = Type.fromString(ctx.Type().getText());

			AnswerableQuestion question = new AnswerableQuestion(identifier, label, type);
			answerableQuestions.put(identifier, question);

			return question.getValue();
		}
		throw new RuntimeException(ERROR_DuplicateQuestionDeclaration + identifier);
	}

	@Override
	public Value visitPartComputedQuestion(ExpressionLanguageParser.PartComputedQuestionContext ctx) {

		Value value = new Value( Type.fromString(ctx.Type().getText()), visitChildren(ctx).getUnits() );
		
		String identifier = ctx.Identifier().getText();
		String label = ctx.QuotedString().getText();

		if (!computedQuestions.containsKey(identifier)) {

			foundReferences.add(identifier);
			
			ComputedQuestion question = new ComputedQuestion(identifier, label, value);
			
			computedQuestions.put(identifier, question);

			return question.getValue();
		}
		throw new RuntimeException(ERROR_DuplicateQuestionDeclaration + identifier);
	}

	@Override
	public Value visitIdentifier(ExpressionLanguageParser.IdentifierContext ctx) {
		
		String identifier = ctx.Identifier().getText();
		forwardReferences.add(identifier);
		return null;
	}

}
