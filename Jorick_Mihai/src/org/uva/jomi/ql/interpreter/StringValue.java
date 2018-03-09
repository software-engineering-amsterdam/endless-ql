package org.uva.jomi.ql.interpreter;

public class StringValue implements GenericValue {
	private final String value;

	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	// Addition.

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		return rightHandSideValue.add(this);
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		String error = additionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		return new StringValue(leftHandSideValue.getValue().concat(this.getValue()));
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		String error = additionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	// Subtraction.

	@Override
	public GenericValue subtract(GenericValue rightHandSideValue) {
		String error = subtractionError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue subtract(IntegerValue leftHandSideValue) {
		String error = subtractionError( leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = multiplicationError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue multiply(IntegerValue leftHandSideValue) {
		String error = multiplicationError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = divisionError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue divide(IntegerValue leftHandSideValue) {
		String error = divisionError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = compareError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue less(IntegerValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = compareError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue lessOrEqual(IntegerValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = compareError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue greater(IntegerValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = compareError(this.getClass(), rightHandSideValue.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue greaterOrEqual(IntegerValue leftHandSideValue) {
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
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
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue equal(StringValue leftHandSideValue) {
		return new BooleanValue(leftHandSideValue.getValue().equals(this.value));
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
		String error = compareError(leftHandSideValue.getClass(), this.getClass());
		throw new RuntimeException(error);
	}

	@Override
	public GenericValue notEqual(StringValue leftHandSideValue) {
		return new BooleanValue(!leftHandSideValue.getValue().equals(this.value));
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
