package org.uva.jomi.ql.interpreter;

public class BooleanValue implements GenericValue {
	private final Boolean value;
	
	public BooleanValue(Boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return value;
	}
	
	// Addition.

	@Override
	public GenericValue add(GenericValue rightHandSideValue) {
		additionError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue add(IntegerValue leftHandSideValue) {
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(BooleanValue leftHandSideValue) {
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue add(StringValue leftHandSideValue) {
		additionError(leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Subtraction.

	@Override
	public GenericValue subtract(GenericValue rightHandSideValue) {
		subtractionError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(IntegerValue leftHandSideValue) {
		subtractionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(StringValue leftHandSideValue) {
		subtractionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue subtract(BooleanValue leftHandSideValue) {
		subtractionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Multiplication.

	@Override
	public GenericValue multiply(GenericValue rightHandSideValue) {
		multiplicationError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(IntegerValue leftHandSideValue) {
		multiplicationError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(StringValue leftHandSideValue) {
		multiplicationError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue multiply(BooleanValue leftHandSideValue) {
		multiplicationError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}
	
	// Division.

	@Override
	public GenericValue divide(GenericValue rightHandSideValue) {
		divisionError(this.getClass(), rightHandSideValue.getClass());
		return null;
	}

	@Override
	public GenericValue divide(IntegerValue leftHandSideValue) {
		divisionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue divide(StringValue leftHandSideValue) {
		divisionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}

	@Override
	public GenericValue divide(BooleanValue leftHandSideValue) {
		divisionError( leftHandSideValue.getClass(), this.getClass());
		return null;
	}
}
