package nl.khonraad.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AnswerableQuestion {

	private String identifier;
	private String label;
	private Value value;

	public AnswerableQuestion(String identifier, String label, Type type) {

		this.identifier = identifier;
		this.label = label;
		
		switch( type ) {
			case Integer:
				this.value = new Value(type, "0");
				break;
			case Boolean:
				this.value = new Value(type, "False");
				break;
			case Money:
				this.value = new Value(type, "0.00");
				break;
			case String:
				this.value = new Value(type, "");
				break;
			case Date:
				this.value = new Value(type, "01/01/1970");
				break;
				
		}
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
	@Override
	public String toString() {

		return new ToStringBuilder( this, ToStringStyle.MULTI_LINE_STYLE ).append( "identifier", identifier ).append( "label", label ).append( "value", value )
				.toString();
	}
}
