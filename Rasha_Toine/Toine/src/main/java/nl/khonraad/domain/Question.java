package nl.khonraad.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Question {

	public enum BehaviouralType {

		ANSWERABLE, COMPUTED;
	}

	private BehaviouralType behaviouralType;
	private String identifier;
	private String label;
	private Value value;

	public Question(BehaviouralType behaviouralType, String identifier, String label, Type type, Value value) {

		this.behaviouralType = behaviouralType;
		this.identifier = identifier;
		this.label = label;
		this.value = value;
	}

	public BehaviouralType getBehaviouralType() {
		return behaviouralType;
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

	public void parseThenSetValue( String s ) {

		this.value = new Value( value.getType(), s );
	}

	@Override
	public String toString() {

		return new ToStringBuilder( this, ToStringStyle.MULTI_LINE_STYLE ).append( "identifier", identifier )
				.append( "label", label ).append( "value", value ).toString();
	}
}
