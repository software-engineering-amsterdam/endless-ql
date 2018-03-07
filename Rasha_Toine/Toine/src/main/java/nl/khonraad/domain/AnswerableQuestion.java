package nl.khonraad.domain;

public class AnswerableQuestion {

	private String identifier;
	private String label;
	private Value value;

	public AnswerableQuestion(String identifier, String label, Type type) {

		this.identifier = identifier;
		this.label = label;
		this.value = new Value(type, 0);
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getLabel() {
		return label;
	}

	public Value getValue() {
		return value;
	}

	public void parseThenSetValue(String s) {

		this.value = new Value(value.getType(), s);
	}
}
