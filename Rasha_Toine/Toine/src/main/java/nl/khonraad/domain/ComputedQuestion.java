package nl.khonraad.domain;

public class ComputedQuestion {

	String identifier;
	String label;
	Value value;

	public ComputedQuestion(String identifier, String label, Value value) {
		
		this.identifier = identifier;
		this.label = label;
		this.value = value;
	}
	
	public Value getValue() {
		return value;
	}

}
