package org.uva.jomi.ui.interpreter.value;

public class BooleanValue implements GenericValue {
	private final Boolean value;

	public BooleanValue(Boolean value) {
		this.value = value;
	}

	@Override
	public Boolean getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.getValue().toString();
	}

	// And operation.

	@Override
	public GenericValue and(GenericValue rightHandSideValue) {
		return rightHandSideValue.and(this);
	}

	@Override
	public GenericValue and(BooleanValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue() && this.getValue());
	}

	// Or operation.

	@Override
	public GenericValue or(GenericValue rightHandSideValue) {
		return rightHandSideValue.or(this);
	}

	@Override
	public GenericValue or(BooleanValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue() || this.getValue());
	}

	// Equal operation.

	@Override
	public GenericValue equal(GenericValue rightHandSideValue) {
		return rightHandSideValue.equal(this);
	}

	@Override
	public GenericValue equal(BooleanValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue().equals(this.getValue()));
	}

	// Not equal operation.

	@Override
	public GenericValue notEqual(GenericValue rightHandSideValue) {
		return rightHandSideValue.notEqual(this);
	}

	@Override
	public GenericValue notEqual(BooleanValue leftHandSideValue) {
		return new BooleanValue(!leftHandSideValue.getValue().equals(this.getValue()));
	}
	
	// Negation.
	@Override
	public GenericValue negate() {
		return new BooleanValue(!this.getValue());
	}
}
