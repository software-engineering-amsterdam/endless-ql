package nl.khonraad.domain;

public class ComputedQuestion extends Value {

	String identifier;
	String label;

	public ComputedQuestion(String identifier, String label, String type) {
		
		super(type, 0);
		
		this.identifier = identifier;
		this.label = label;
	}

}
