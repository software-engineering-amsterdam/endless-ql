package nl.khonraad.domain;

public class AnswerableQuestion extends Value {

	String identifier;
	String label;

	// An undefined boolean will therefore be considered False, a date as EPOCH.

	private static int UNDEFINED_VALUE = 0;

	public AnswerableQuestion(String identifier, String label, Type type) {

		super(type, UNDEFINED_VALUE);

		this.identifier = identifier;
		this.label = label;
	}

}
