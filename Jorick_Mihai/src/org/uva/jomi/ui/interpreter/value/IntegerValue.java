package org.uva.jomi.ui.interpreter.value;

public class IntegerValue implements GenericValue {
	private final Integer value;

	public IntegerValue(Integer value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.getValue().toString();
	}

	// Addition.

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		return rightHandSideValue.add(this);
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		return new IntegerValue(leftHandSideValue.getValue() + this.getValue());
	}

	// Subtraction.

	@Override
	public GenericValue subtract(GenericValue rightHandSideValue) {
		return rightHandSideValue.subtract(this);
	}

	@Override
	public GenericValue subtract(IntegerValue leftHandSideValue) {
		return new IntegerValue(leftHandSideValue.getValue() - this.getValue());
	}

	// Multiplication.

	@Override
	public GenericValue multiply(GenericValue rightHandSideValue) {
		return rightHandSideValue.multiply(this);
	}

	@Override
	public GenericValue multiply(IntegerValue leftHandSideValue) {
		return new IntegerValue(leftHandSideValue.getValue() * this.getValue());
	}

	// Division.

	@Override
	public GenericValue divide(GenericValue rightHandSideValue) {
		return rightHandSideValue.divide(this);
	}

	@Override
	public GenericValue divide(IntegerValue leftHandSideValue) {
		return new IntegerValue(leftHandSideValue.getValue() / this.getValue());
	}

	// Less than operation.

	@Override
	public GenericValue less(GenericValue rightHandSideValue) {
		return rightHandSideValue.less(this);
	}

	@Override
	public GenericValue less(IntegerValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue() < this.getValue());
	}

	// Less than or equal operation.

	@Override
	public GenericValue lessOrEqual(GenericValue rightHandSideValue) {
		return rightHandSideValue.lessOrEqual(this);
	}

	@Override
	public GenericValue lessOrEqual(IntegerValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue() <= this.getValue());
	}

	// Greater than operation.

	@Override
	public GenericValue greater(GenericValue rightHandSideValue) {
		return rightHandSideValue.greater(this);
	}

	@Override
	public GenericValue greater(IntegerValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue() > this.getValue());
	}

	// Greater than or equal operation.

	@Override
	public GenericValue greaterOrEqual(GenericValue rightHandSideValue) {
		return rightHandSideValue.greaterOrEqual(this);
	}

	@Override
	public GenericValue greaterOrEqual(IntegerValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue() >= this.getValue());
	}

	// Equal operation.

	@Override
	public GenericValue equal(GenericValue rightHandSideValue) {
		return rightHandSideValue.equal(this);
	}

	@Override
	public GenericValue equal(IntegerValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue().equals(this.getValue()));
	}

	// Not equal operation.

	@Override
	public GenericValue notEqual(GenericValue rightHandSideValue) {
		return rightHandSideValue.notEqual(this);
	}

	@Override
	public GenericValue notEqual(IntegerValue leftHandSideValue) {
		return new BooleanValue(!leftHandSideValue.getValue().equals(this.getValue()));
	}
}
