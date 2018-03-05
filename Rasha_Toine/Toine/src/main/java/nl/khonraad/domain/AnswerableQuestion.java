package nl.khonraad.domain;

public class AnswerableQuestion extends Value {

	String identifier;
	String label;

	public AnswerableQuestion(String identifier, String label, String type) {
		
		super(type, 0);
		
		this.identifier = identifier;
		this.label = label;
	}

}
