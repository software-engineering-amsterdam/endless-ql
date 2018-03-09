package org.uva.jomi.ui.interpreter.value;

public class IntegerValue implements GenericValue {
	private final Integer value;

	public IntegerValue(Integer value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
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

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		String error = additionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		String error = additionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue subtract(StringValue leftHandSideValue) {
		String error = subtractionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue subtract(BooleanValue leftHandSideValue) {
		String error = subtractionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue multiply(StringValue leftHandSideValue) {
		String error = multiplicationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue multiply(BooleanValue leftHandSideValue) {
		String error = multiplicationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue divide(StringValue leftHandSideValue) {
		String error = divisionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue divide(BooleanValue leftHandSideValue) {
		String error = divisionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	// And operation.

	@Override
	public GenericValue and(GenericValue rightHandSideValue) {
		String error = andOperationError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue and(IntegerValue leftHandSideValue) {
		String error = andOperationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue and(StringValue leftHandSideValue) {
		String error = andOperationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue and(BooleanValue leftHandSideValue) {
		String error = andOperationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	// Or operation.

	@Override
	public GenericValue or(GenericValue rightHandSideValue) {
		String error = orOperationError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue or(IntegerValue leftHandSideValue) {
		String error = orOperationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue or(StringValue leftHandSideValue) {
		String error = orOperationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue or(BooleanValue leftHandSideValue) {
		String error = orOperationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue less(StringValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue less(BooleanValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue lessOrEqual(StringValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue lessOrEqual(BooleanValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue greater(StringValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue greater(BooleanValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue greaterOrEqual(StringValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue greaterOrEqual(BooleanValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue equal(StringValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue equal(BooleanValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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

	@Override
	public GenericValue notEqual(StringValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue notEqual(BooleanValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue negate() {
		String error = negationError(this.getClass());
		throw new RuntimeException(error);
	}
}
