package nl.khonraad.domain;

public class AnswerableQuestion extends Value {

	String identifier;
	String label;

	public AnswerableQuestion(String identifier, String label, Type type) {
		
		super(type, 0);
		
		this.identifier = identifier;
		this.label = label;
	}

}
