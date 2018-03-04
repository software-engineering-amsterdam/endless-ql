package org.uva.jomi.ql.interpreter;

public class IntegerValue implements GenericValue {
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
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
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
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
		subtractionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(BooleanValue leftHandSideValue) {
		subtractionError(leftHandSideValue.getClass(), this.getClass());
		return null;
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
		multiplicationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(BooleanValue leftHandSideValue) {
		multiplicationError(leftHandSideValue.getClass(), this.getClass());
		return null;
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
		divisionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue divide(BooleanValue leftHandSideValue) {
		divisionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// And operation.

	@Override
	public GenericValue and(GenericValue rightHandSideValue) {
		andOperationError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue and(IntegerValue leftHandSideValue) {
		andOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue and(StringValue leftHandSideValue) {
		andOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue and(BooleanValue leftHandSideValue) {
		andOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Or operation.

	@Override
	public GenericValue or(GenericValue rightHandSideValue) {
		orOperationError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue or(IntegerValue leftHandSideValue) {
		orOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue or(StringValue leftHandSideValue) {
		orOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue or(BooleanValue leftHandSideValue) {
		orOperationError(leftHandSideValue.getClass(), this.getClass());
		return null;
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
		compareError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue less(BooleanValue leftHandSideValue) {
		compareError(leftHandSideValue.getClass(), this.getClass());
		return null;
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
		compareError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue lessOrEqual(BooleanValue leftHandSideValue) {
		compareError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}	
}
