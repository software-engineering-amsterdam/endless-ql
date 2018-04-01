package org.uva.jomi.ui.interpreter.value;

public class StringValue implements GenericValue {
	private final String value;

	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}

	// Addition.

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		return rightHandSideValue.add(this);
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		return new StringValue(leftHandSideValue.getValue().concat(this.getValue()));
	}

	// Equal operation.

	@Override
	public GenericValue equal(GenericValue rightHandSideValue) {
		return rightHandSideValue.equal(this);
	}

	@Override
	public GenericValue equal(StringValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue().equals(this.value));
	}

	// Not equal operation.

	@Override
	public GenericValue notEqual(GenericValue rightHandSideValue) {
		return rightHandSideValue.notEqual(this);
	}

	@Override
	public GenericValue notEqual(StringValue leftHandSideValue) {
		return new BooleanValue(!leftHandSideValue.getValue().equals(this.value));
	}
}
